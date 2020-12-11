package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class ExistsSuchThatNode extends BinaryNode{
    private String variableName;

    public ExistsSuchThatNode(Node leftNode, Node rightNode, String variableName) {
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
            /*HashMap<String, Object> nestedHashmap = new HashMap<>(interpretation.getEnvironment());
            Interpretation nestedInterpretation = new Interpretation(nestedHashmap);
            nestedInterpretation.getEnvironment().put(variableName, o);
            Boolean result = (Boolean) getRightNode().evaluateNode(nestedInterpretation);*/

            interpretation.getEnvironment().put(variableName, o);
            Boolean result = (Boolean) getRightNode().evaluateNode(interpretation);
            interpretation.getEnvironment().remove(variableName);
            if(result){
                return true;
            }
        }
        return false;
    }
}
