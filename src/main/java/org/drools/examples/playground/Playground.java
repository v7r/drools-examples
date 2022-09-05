package org.drools.examples.playground;

import dd.drools.model.Estimate;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static dd.drools.toolkit.Utils.logFacts;

public class Playground {

    public static void main(final String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        execute( kc );
    }

    public static void execute( KieContainer kc ) {
        KieSession ksession = kc.newKieSession("PlaygroundKS");

        ksession.insert(new Estimate());
        ksession.fireAllRules();

        logFacts(ksession);
        ksession.dispose(); // Stateful rule session must always be disposed when finished
    }
}

