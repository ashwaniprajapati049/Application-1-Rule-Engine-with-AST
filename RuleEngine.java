import java.util.*;

public class RuleEngine {

    // Parses a rule string and creates an AST
    public Node createRule(String ruleString) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = ruleString.split(" ");
        for (String token : tokens) {
            if (token.equals("AND") || token.equals("OR")) {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new Node("operator", left, right, token));
            } else {
                stack.push(new Node("operand", null, null, token));
            }
        }
        return stack.peek();
    }

    // Combines multiple ASTs into one
    public Node combineRules(List<String> rules) {
        List<Node> nodes = new ArrayList<>();
        for (String rule : rules) {
            nodes.add(createRule(rule));
        }
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            root = new Node("operator", root, nodes.get(i), "AND");
        }
        return root;
    }

    // Evaluates the rule AST for the given user data
    public boolean evaluateRule(Node node, Map<String, Object> data) {
        if (node.type.equals("operand")) {
            String[] condition = node.value.split(" ");
            String key = condition[0];
            String operator = condition[1];
            Object value = data.get(key);
            return evaluateCondition(value, operator, condition[2]);
        } else if (node.type.equals("operator")) {
            boolean leftResult = evaluateRule(node.left, data);
            boolean rightResult = evaluateRule(node.right, data);
            return node.value.equals("AND") ? leftResult && rightResult : leftResult || rightResult;
        }
        return false;
    }

    private boolean evaluateCondition(Object actual, String operator, String expected) {
        if (actual instanceof Integer) {
            int actualValue = (Integer) actual;
            int expectedValue = Integer.parseInt(expected);
            switch (operator) {
                case ">": return actualValue > expectedValue;
                case "<": return actualValue < expectedValue;
                case "==": return actualValue == expectedValue;
            }
        }
        return false;
    }
}
