package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class ForAllSuchThatNode extends BinaryPredicateNode{
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
        Object tuple = getLeftNode().evaluateNode(interpretation);
        if(interpretation.getEnvironment().containsKey(variableName)){
            throw new VariableAlreadyDefinedException(variableName);
        }
        for(Object o : ((Tuple) tuple).getElements()){
            //TODO evtl umändern so wie bei ExistsSuchThatNode
            interpretation.getEnvironment().put(variableName, o);
            Object result = getRightNode().evaluateNode(interpretation);
            if(result instanceof Boolean) {
                if (!((Boolean)result)) {
                    interpretation.getEnvironment().remove(variableName);
                    return false;
                }
            }
        }
        interpretation.getEnvironment().remove(variableName);
        return true;
    }
}
