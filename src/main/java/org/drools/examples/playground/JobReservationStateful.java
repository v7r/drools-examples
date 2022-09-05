package org.drools.examples.playground;

import dd.drools.model.Job;
import dd.drools.model.JobMaterial;
import dd.drools.service.DataService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dd.drools.toolkit.Utils.logFacts;

public class JobReservationStateful {
    public static void main(final String[] args) throws Exception {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        execute( kc );
    }

    /**
     * Bootstraps the inventory data. Then creates a Job with quantity. Drools logic should reserve the material quantity
     * required by the job only if inventory levels are sufficient else rejects.
     * @param kc
     */
    public static void execute( KieContainer kc ) throws Exception {
        DataService.bootstrap();

        KieSession ksession = kc.newKieSession("JobReservationStatfulKS");

        JobMaterial jobMaterial = new JobMaterial();
        jobMaterial.material = DataService.fetchMaterial(DataService.MATERIAL_STRIP_FLOORING).get();
        jobMaterial.quantity = 700.0;

        Job job1 = new Job();
        job1.jobLocation = "Some place in Calgary";
        job1.jobMaterials.add(jobMaterial);
        job1.id = 501;

        ksession.setGlobal("errors", new ArrayList<String>());
        ksession.setGlobal("messages", new ArrayList<String>(Arrays.asList(new String[]{"Empty"})));
        ksession.insert(job1);
        ksession.fireAllRules();
        List<String> errors = (List<String>)ksession.getGlobal("errors");
        List<String> messages = (List<String>)ksession.getGlobal("messages");

        System.out.println("Messages: "+messages);
        System.err.println("Errors: "+errors);

        logFacts(ksession);
        ksession.dispose(); // Stateful rule session must always be disposed when finished
    }
}
