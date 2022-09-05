package org.drools.examples.propagation;

import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItemManager;

import java.util.Collection;
import java.util.function.Consumer;

public class PropagationExample {

    public static void main(final String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        execute( kc );
    }

    public static void execute( KieContainer kc ) {
        KieSession ksession = kc.newKieSession("PropagationKS");

        ksession.insert(1);
        ksession.insert("1");
        ksession.fireAllRules();

        Collection<Object> myfacts = (Collection<Object>) ksession.getObjects( new ClassObjectFilter(Object.class) );
        if (myfacts != null) {
            System.out.println("Facts in the rules engine");
            myfacts.forEach(it -> { System.out.println("Fact: "+it+"("+it.getClass()+")"); });
        } else {
            System.out.println("No facts retrieved!");
        }

        ksession.dispose(); // Stateful rule session must always be disposed when finished
    }
}

