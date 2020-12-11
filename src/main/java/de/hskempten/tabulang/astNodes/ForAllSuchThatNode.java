package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class ForAllSuchThatNode extends BinaryNode{
    private String variableName;

    public ForAllSuchThatNode(Node leftNode, Node rightNode, String variableName) {
        super(leftNode, rightNode);
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Tuple t = (Tuple) getLeftNode().evaluateNode(interpretation);
        if(interpretation.getEnvironment().containsKey(variableName)){
            throw new VariableAlreadyDefinedException(variableName);
        }
        for(Object o : t.getElements()){
            //TODO evtl umändern so wie bei ExistsSuchThatNode
            interpretation.getEnvironment().put(variableName, o);
            Boolean result = (Boolean) getRightNode().evaluateNode(interpretation);
            if(!result){
                interpretation.getEnvironment().remove(variableName);
                return false;
            }
        }
        interpretation.getEnvironment().remove(variableName);

        return true;
    }
}
