package org.drools.examples.playground;

import dd.drools.model.*;
import dd.drools.service.DataService;
import dd.drools.toolkit.Utils;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

import java.util.*;

public class JobReservationStateless {
    public static void main(final String[] args) throws Exception {
        //KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        /*KieServices kieService = KieServices.Factory.get();
        KieBaseConfiguration config = kieService.newKieBaseConfiguration();
        KieContainer kc = kieService.getKieClasspathContainer();
        KieBase kbase = kc.newKieBase(config);*/
        execute( kc );
    }

    /**
     * Bootstraps the inventory data. Then creates a Job with quantity. Drools logic should reserve the material quantity
     * required by the job only if inventory levels are sufficient else rejects.
     * @param kc
     */
    public static void execute( KieContainer kc ) throws Exception {
        DataService.bootstrap();

        StatelessKieSession ksession = kc.newStatelessKieSession("JobReservationKS");

        JobMaterial jobMaterial = new JobMaterial();
        jobMaterial.material = DataService.fetchMaterial(DataService.MATERIAL_STRIP_FLOORING).get();
        jobMaterial.quantity = 1100.0;

        Map reqData = new HashMap();
        reqData.put("action","createJob");
        reqData.put("saleValue",221.00);
        reqData.put("jobLocation","Calgary");
        reqData.put("invoiceNumber","123121");
        reqData.put("jobMaterials", Arrays.asList(new Object[] {jobMaterial}));

        Job job1 = new Job();
        job1.jobLocation = "Some place in Calgary";
        job1.jobMaterials.add(jobMaterial);
        job1.id = 501;

        List commands = new ArrayList();
        //Execution-scoped globals. Other scope is sesssion scoped. Where you can set in ksession.setGlobal
        commands.add(CommandFactory.newSetGlobal("errors", new ArrayList<String>(), true));
        commands.add(CommandFactory.newSetGlobal("messages", new ArrayList<String>(), true));
        //commands.add(CommandFactory.newInsert(job1, "jobHandle"));
        commands.add(CommandFactory.newInsert(reqData, "jobReqHandle"));

        ksession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void matchCancelled(MatchCancelledEvent event) {
                Utils.logMessage("Match cancelled "+event);
            }
        });

        ExecutionResults results = ksession.execute(CommandFactory.newBatchExecution(commands));
        List<String> messages = (List<String>)results.getValue("messages");
        List<String> errors = (List<String>)results.getValue("errors");

        System.out.println("Messages: "+messages);
        System.err.println("Errors: "+errors);
    }
}
