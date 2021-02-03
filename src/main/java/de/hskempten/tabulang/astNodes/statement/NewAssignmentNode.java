package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class NewAssignmentNode extends BinaryStatementNode {
    public NewAssignmentNode(IdentifierNode leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        String left = ((IdentifierNode) getLeftNode()).getIdentifier();
        Object right = getRightNode().evaluateNode(interpretation);
        interpretation.getEnvironment().put(left, right);
        return right;
    }
}
