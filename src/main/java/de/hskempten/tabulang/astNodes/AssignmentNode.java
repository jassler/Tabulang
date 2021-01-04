package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.Objects;

public class AssignmentNode extends BinaryStatementNode {

    public AssignmentNode(IdentifierNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
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
