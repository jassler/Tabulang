package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public abstract class BinaryNode extends Node{
    private Node leftNode;
    private Node rightNode;

    public BinaryNode(Node leftNode, Node rightNode) {
        super(NodeType.BINARY);
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

    public BigDecimal convertLeftNodeToBigDecimal(Interpretation i){
        return (BigDecimal) getLeftNode().evaluateNode(i);
    }

    public BigDecimal convertRightNodeToBigDecimal(Interpretation i){
        return (BigDecimal) getRightNode().evaluateNode(i);
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                "} ";
    }
}
