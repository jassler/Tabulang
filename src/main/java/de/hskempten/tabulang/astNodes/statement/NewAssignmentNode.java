package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class NewAssignmentNode extends BinaryStatementNode {
    public NewAssignmentNode(IdentifierNode leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Assigns specified value to specified identifier key in the interpretation.
     * This node does not check if the key is already in use in the current or any of the parent interpretations
     * and always assigns the value at the current interpretation.
     *
     * @return value that got assigned to identifier.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        String left = ((IdentifierNode) getLeftNode()).getIdentifier();
        Object right = getRightNode().evaluateNode(interpretation);
        interpretation.getEnvironment().put(left, right);
        return right;
    }
}
