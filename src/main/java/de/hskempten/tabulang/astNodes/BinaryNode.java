package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class BinaryNode extends Node {
    private Node leftNode;
    private Node rightNode;

    public BinaryNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(textPosition);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
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
        return "BinaryNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                "} ";
    }
}
