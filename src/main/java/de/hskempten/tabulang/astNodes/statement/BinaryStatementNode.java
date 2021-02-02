package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.statement.StatementNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class BinaryStatementNode extends StatementNode {
    private Node leftNode;
    private Node rightNode;

    public BinaryStatementNode(Node leftNode, Node rightNode, TextPosition textPosition) {
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
}
