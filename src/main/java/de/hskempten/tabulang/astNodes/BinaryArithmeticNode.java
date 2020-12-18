package de.hskempten.tabulang.astNodes;

public abstract class BinaryArithmeticNode extends ArithmeticNode{
    private TermNode leftNode;
    private TermNode rightNode;

    public BinaryArithmeticNode(TermNode leftNode, TermNode rightNod) {
        this.leftNode = leftNode;
        this.rightNode = rightNod;
    }

    public TermNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TermNode leftNode) {
        this.leftNode = leftNode;
    }

    public TermNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TermNode rightNode) {
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
