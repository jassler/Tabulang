package de.hskempten.tabulang.astNodes;

public abstract class QuaternaryTermNode extends TermNode{
    private Node left;
    private Node middleLeft;
    private Node middleRight;
    private Node right;

    public QuaternaryTermNode(Node left, Node middleLeft, Node middleRight, Node right) {
        this.left = left;
        this.middleLeft = middleLeft;
        this.middleRight = middleRight;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getMiddleLeft() {
        return middleLeft;
    }

    public void setMiddleLeft(Node middleLeft) {
        this.middleLeft = middleLeft;
    }

    public Node getMiddleRight() {
        return middleRight;
    }

    public void setMiddleRight(Node middleRight) {
        this.middleRight = middleRight;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
