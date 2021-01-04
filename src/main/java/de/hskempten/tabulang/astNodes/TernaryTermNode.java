package de.hskempten.tabulang.astNodes;

public abstract class TernaryTermNode extends TermNode{
    private Node leftNode;
    private Node middleNode;
    private Node rightNode;

    public TernaryTermNode(Node leftNode, Node middleNode, Node rightNode) {
        this.leftNode = leftNode;
        this.middleNode = middleNode;
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getMiddleNode() {
        return middleNode;
    }

    public void setMiddleNode(Node middleNode) {
        this.middleNode = middleNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
