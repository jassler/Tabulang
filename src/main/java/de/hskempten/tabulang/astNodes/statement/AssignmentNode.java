package de.hskempten.tabulang.astNodes.statement;


import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.Interpretation;
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
