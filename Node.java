public class Node {
    // Enum to represent node types (operator or operand)
    public enum NodeType {
        OPERATOR, OPERAND
    }

    private NodeType type;  // Operator or Operand
    private Node left;
    private Node right;
    private String value;   // Operand value (e.g., age, salary)

    // Constructor
    public Node(NodeType type, Node left, Node right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    // Getters and Setters
    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Override toString for better debugging
    @Override
    public String toString() {
        return "Node{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", left=" + (left != null ? left.toString() : "null") +
                ", right=" + (right != null ? right.toString() : "null") +
                '}';
    }
}
