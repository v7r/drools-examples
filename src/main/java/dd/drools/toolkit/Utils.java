package dd.drools.toolkit;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Collection;

/**
 *
 */
public class Utils {

    /**
     * Logs the facts in the given kession
     * @param ksession
     */
    public static void logFacts(KieSession ksession) {
        Collection<Object> myfacts = (Collection<Object>) ksession.getObjects( new ClassObjectFilter(Object.class) );
        if (myfacts != null) {
            System.out.println("Facts in the rules engine");
            myfacts.forEach(it -> { System.out.println("\tFact: "+it+"("+it.getClass()+")"); });
        } else {
            System.out.println("No facts retrieved!");
        }
    }

    public static void logMessage(String message) {
        System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().getId()+" "+message);
    }
}
