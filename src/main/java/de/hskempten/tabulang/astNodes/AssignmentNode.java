package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class AssignmentNode extends BinaryStatementNode {

    public AssignmentNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(right instanceof InternalNumber){
            right = ((InternalNumber) right).getFloatValue();
        }
        if(right instanceof Identifier){
            Interpretation found = interpretation.findIdentifier((Identifier) right);
            if(found == null){
                throw new VariableNotInitializedException((((Identifier) right).getIdentifierName()));
            }
            right = found.getEnvironment().get(((Identifier) right).getIdentifierName());
        }
        if(left instanceof Identifier){
            Interpretation found = interpretation.findIdentifier((Identifier) left);
            if(found == null){
                interpretation.getEnvironment().put(((Identifier) left).getIdentifierName(), right);
            } else {
                found.getEnvironment().put(((Identifier) left).getIdentifierName(), right);
            }
        } else {
            throw new IllegalArgumentException("Expected Identifier but got: " + left.getClass().getSimpleName());
        }
        return right;
    }

    @Override
    public String toString() {
        return "AssignmentNode{} " + super.toString();
    }
}
