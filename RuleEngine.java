import java.util.*;

/**
 * The RuleEngine class processes rules defined as strings, creates an Abstract Syntax Tree (AST) representation,
 * combines multiple ASTs, and evaluates them against user data.
 */
public class RuleEngine {

    /**
     * Parses a rule string and creates an AST.
     *
     * @param ruleString The rule string to be parsed (e.g., "age > 30 AND department == 'Sales'").
     * @return The root Node of the generated AST.
     */
    public Node createRule(String ruleString) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = ruleString.split(" ");
        
        for (String token : tokens) {
            if (token.equals("AND") || token.equals("OR")) {
                // Pop two nodes for the operator
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new Node(Node.NodeType.OPERATOR, left, right, token));
            } else {
                // Create an operand node
                stack.push(new Node(Node.NodeType.OPERAND, null, null, token));
            }
        }
        return stack.peek();  // Return the root of the AST
    }

    /**
     * Combines multiple ASTs into a single AST using AND logic.
     *
     * @param rules A list of rule strings to combine.
     * @return The root Node of the combined AST.
     */
    public Node combineRules(List<String> rules) {
        List<Node> nodes = new ArrayList<>();
        for (String rule : rules) {
            nodes.add(createRule(rule));
        }
        
        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            root = new Node(Node.NodeType.OPERATOR, root, nodes.get(i), "AND");
        }
        return root;
    }

    /**
     * Evaluates the rule AST for the given user data.
     *
     * @param node The root Node of the AST to evaluate.
     * @param data A map containing user attributes (e.g., age, department).
     * @return True if the user meets the criteria defined by the rule, otherwise False.
     */
    public boolean evaluateRule(Node node, Map<String, Object> data) {
        if (node.getType() == Node.NodeType.OPERAND) {
            String[] condition = node.getValue().split(" ");
            String key = condition[0];
            String operator = condition[1];
            Object value = data.get(key);
            return evaluateCondition(value, operator, condition[2]);
        } else if (node.getType() == Node.NodeType.OPERATOR) {
            boolean leftResult = evaluateRule(node.getLeft(), data);
            boolean rightResult = evaluateRule(node.getRight(), data);
            return node.getValue().equals("AND") ? leftResult && rightResult : leftResult || rightResult;
        }
        return false; // Return false for invalid node types
    }

    /**
     * Evaluates a single condition based on the operator.
     *
     * @param actual The actual value from user data.
     * @param operator The operator for the comparison (>, <, ==).
     * @param expected The expected value to compare against.
     * @return True if the condition holds, otherwise False.
     */
    private boolean evaluateCondition(Object actual, String operator, String expected) {
        if (actual instanceof Integer) {
            int actualValue = (Integer) actual;
            int expectedValue = Integer.parseInt(expected);
            switch (operator) {
                case ">": return actualValue > expectedValue;
                case "<": return actualValue < expectedValue;
                case "==": return actualValue == expectedValue;
                default: return false; // Unsupported operator
            }
        }
        return false; // Return false if the actual value is not an Integer
    }
}
