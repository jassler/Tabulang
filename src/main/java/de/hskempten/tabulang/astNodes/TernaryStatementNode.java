package de.hskempten.tabulang.astNodes;

public abstract class TernaryStatementNode extends StatementNode{
    private Node left;
    private Node middle;
    private Node right;

    public TernaryStatementNode(Node left, Node middle, Node right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getMiddle() {
        return middle;
    }

    public void setMiddle(Node middle) {
        this.middle = middle;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
