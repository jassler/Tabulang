package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;

public abstract class BinaryTermNode extends TermNode {
    private Node leftNode;
    private Node rightNode;

    public BinaryTermNode(Node leftNode, Node rightNode) {
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

}
