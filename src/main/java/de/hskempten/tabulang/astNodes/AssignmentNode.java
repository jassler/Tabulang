package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Objects;

public class AssignmentNode extends BinaryStatementNode {

    public AssignmentNode(IdentifierNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        String left = ((IdentifierNode) getLeftNode()).getIdentifier();
        Object right = getRightNode().evaluateNode(interpretation);
        Interpretation foundIdentifier = interpretation.findIdentifier(left);
        Objects.requireNonNullElse(foundIdentifier, interpretation).getEnvironment().put(left, right);
        return right;
    }

    @Override
    public String toString() {
        return getLeftNode() + " := " + getRightNode();
    }
}
