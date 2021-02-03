package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IfElseNode extends TernaryStatementNode {
    public IfElseNode(Node leftNode, Node middleNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, middleNode, rightNode, textPosition);
    }

    /**
     * Executes either one of two nodes according to boolean value of a third evaluated node.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean internalBoolean = getLeftNode().verifyAndReturnBoolean(interpretation);
        if (internalBoolean.getBoolean()) {
            return getMiddleNode().evaluateNode(interpretation);
        } else {
            return getRightNode().evaluateNode(interpretation);
        }
    }

    @Override
    public String toString() {
        return "if(" + getLeftNode() + ") " + getMiddleNode() + " else " + getRightNode();
    }
}
