package de.hskempten.tabulang.astNodes;

public abstract class BinaryArithmeticNode extends ArithmeticNode{
    private Node leftNode;
    private Node rightNode;

    public BinaryArithmeticNode(Node leftNode, Node rightNod) {
        this.leftNode = leftNode;
        this.rightNode = rightNod;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "BinaryArithmeticNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                "} " + super.toString();
    }
}
