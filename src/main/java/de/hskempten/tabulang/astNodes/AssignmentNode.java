package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class AssignmentNode extends BinaryStatementNode {

    public AssignmentNode(IdentifierNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if(getLeftNode() instanceof IdentifierNode) {
            String left = ((IdentifierNode) getLeftNode()).getIdentifier();
            Object right = getRightNode().evaluateNode(interpretation);
            if (right instanceof Identifier) {
                Interpretation found = interpretation.findIdentifier((Identifier) right);
                if (found == null) {
                    throw new VariableNotInitializedException((((Identifier) right).getIdentifierName()));
                }
                right = found.getEnvironment().get(((Identifier) right).getIdentifierName());
            }
            Interpretation foundIdentifier = interpretation.findIdentifierTest(left);
            if(foundIdentifier == null){
                interpretation.getEnvironment().put(left, right);
            } else {
                foundIdentifier.getEnvironment().put(left, right);
            }
            return right;
        } else {
            throw new IllegalArgumentException("Left side no identifier");
        }
    }

    @Override
    public String toString() {
        return "AssignmentNode{} " + super.toString();
    }
}
