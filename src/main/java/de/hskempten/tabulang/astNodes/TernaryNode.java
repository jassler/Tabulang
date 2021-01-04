package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class TernaryNode extends Node {
    private Node left;
    private Node middle;
    private Node right;

    public TernaryNode(Node left, Node middle, Node right, TextPosition textPosition) {
        super(textPosition);
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

    @Override
    public String toString() {
        return "TernaryNode{" +
                "left=" + left +
                ", middle=" + middle +
                ", right=" + right +
                "} ";
    }
}
