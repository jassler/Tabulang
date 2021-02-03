package de.hskempten.tabulang.astNodes.term;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class TernaryTermNode extends TermNode {
    private Node leftNode;
    private Node middleNode;
    private Node rightNode;

    public TernaryTermNode(Node leftNode, Node middleNode, Node rightNode, TextPosition textPosition) {
        super(textPosition);
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
