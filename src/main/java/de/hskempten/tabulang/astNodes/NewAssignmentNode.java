package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class NewAssignmentNode extends BinaryStatementNode{
    public NewAssignmentNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(right instanceof Identifier){
            Interpretation found = interpretation.findIdentifier((Identifier) right);
            if(found == null){
                throw new VariableNotInitializedException((((Identifier) right).getIdentifierName()));
            }
            right = found.getEnvironment().get(((Identifier) right).getIdentifierName());
        }
        if(left instanceof Identifier){
            interpretation.getEnvironment().put(((Identifier) left).getIdentifierName(), right);
        } else {
            throw new IllegalArgumentException("Expected Identifier but got: " + left.getClass().getSimpleName());
        }
        return right;
    }
}
