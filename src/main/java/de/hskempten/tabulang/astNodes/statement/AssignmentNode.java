package de.hskempten.tabulang.astNodes.statement;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Objects;

public class AssignmentNode extends BinaryStatementNode {

    public AssignmentNode(IdentifierNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Assigns specified value to specified identifier key in the interpretation.
     * This node checks if the key is already in use in the current or any of the parent interpretations
     * and assigns the value at that location. If not in use, it assigns it at the current interpretation.
     *
     * @return value that got assigned to identifier.
     */
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
