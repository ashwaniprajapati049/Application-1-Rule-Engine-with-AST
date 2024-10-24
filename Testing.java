import java.util.HashMap;
import java.util.Map;

/**
 * RuleEngineTest is a simple test class to demonstrate the functionality of the RuleEngine.
 */
public class RuleEngineTest {
    public static void main(String[] args) {
        // Create an instance of RuleEngine
        RuleEngine ruleEngine = new RuleEngine();
        
        // Define a rule as a string
        String rule = "age > 30 AND department == 'Sales'";
        
        // Create an Abstract Syntax Tree (AST) from the rule
        Node ast = ruleEngine.createRule(rule);
        System.out.println("AST Created: " + ast);  // Display the created AST

        // Prepare user data for evaluation
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);  // User's age
        userData.put("department", "Sales");  // User's department
        
        // Evaluate the rule against the user data
        boolean result = ruleEngine.evaluateRule(ast, userData);
        System.out.println("Evaluation Result: " + result);  // Expected: true
    }
}
