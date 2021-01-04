package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public abstract class BinaryPredicateNode extends PredicateNode{
    private Node leftNode;
    private Node rightNode;

    public BinaryPredicateNode(Node leftNode, Node rightNode) {
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
        return "BinaryPredicateNode{" +
                "leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                "} ";
    }
}
