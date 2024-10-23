public class RuleEngineTest {
    public static void main(String[] args) {
        RuleEngine ruleEngine = new RuleEngine();
        String rule = "age > 30 AND department == 'Sales'";
        Node ast = ruleEngine.createRule(rule);
        System.out.println("AST Created: " + ast.value);

        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);
        userData.put("department", "Sales");
        boolean result = ruleEngine.evaluateRule(ast, userData);
        System.out.println("Evaluation Result: " + result);  // Expected: true
    }
}
