package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

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
        Object val = null;
        if(interpretation.getEnvironment().containsKey(variableName)){
            val = interpretation.getEnvironment().get(variableName);
        }
        for(Object o : t.getElements()){
            interpretation.getEnvironment().put(variableName, o);
            Boolean result = (Boolean) getRightNode().evaluateNode(interpretation);
            if(result){
                //i.getEnvironment().remove(variableName);
                interpretation.getEnvironment().put(variableName, val);
                return true;
            }
        }
        //i.getEnvironment().remove(variableName);
        interpretation.getEnvironment().put(variableName, val);
        return false;
    }
}
