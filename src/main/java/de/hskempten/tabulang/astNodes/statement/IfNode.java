package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IfNode extends BinaryStatementNode {
    public IfNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Executes either one or no node according to boolean value of a third evaluated node.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean internalBoolean = getLeftNode().verifyAndReturnBoolean(interpretation);
        if (internalBoolean.getBoolean()) {
            return getRightNode().evaluateNode(interpretation);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "if(" + getLeftNode() + ") " + getRightNode();
    }
}
