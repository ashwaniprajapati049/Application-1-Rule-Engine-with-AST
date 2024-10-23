class Node {
    String type;  // "operator" or "operand"
    Node left;
    Node right;
    String value;  // Operand value (e.g., age, salary)

    public Node(String type, Node left, Node right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
