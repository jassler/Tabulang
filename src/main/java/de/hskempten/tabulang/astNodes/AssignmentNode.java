package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class AssignmentNode extends BinaryNode {
    private Boolean newVar;

    public AssignmentNode(Node leftNode, Node rightNode, Boolean newVar) {
        super(leftNode, rightNode);
        this.newVar = newVar;
    }

    public Boolean getNewVar() {
        return newVar;
    }

    public void setNewVar(Boolean newVar) {
        this.newVar = newVar;
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
            if(newVar){
                interpretation.getEnvironment().put(((Identifier) left).getIdentifierName(), right);
            }
            else {
                //TODO ausgeklammerten Code entfernen falls found nie null sein kann
                // da newVar == false -> identifier muss schon in einem scope definiert sein;
                /*Interpretation found = interpretation.findIdentifier((Identifier) left);
                if(found == null){
                    interpretation.getEnvironment().put(((Identifier) left).getIdentifierName(), right);
                } else {
                    found.getEnvironment().put(((Identifier) left).getIdentifierName(), right);
                }*/
                Interpretation found = interpretation.findIdentifier((Identifier) left);
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
