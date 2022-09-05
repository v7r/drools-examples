package dd.drools.conf;

import org.kie.api.runtime.rule.ConsequenceExceptionHandler;
import org.kie.api.runtime.rule.Match;
import org.kie.api.runtime.rule.RuleRuntime;

public class DivineExceptionHandler implements ConsequenceExceptionHandler {

    @Override
    public void handleException(Match match, RuleRuntime workingMemory, Exception exception) {
        System.out.println("Divine Drools exception handler =====> Match: "+match+", ruleRuntime: "+workingMemory);
        System.out.println("\nException trace::");
        exception.printStackTrace();
    }
}
