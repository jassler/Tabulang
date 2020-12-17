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
        Object tuple = getLeftNode().evaluateNode(interpretation);
        if(tuple instanceof Tuple) {
            if (interpretation.getEnvironment().containsKey(variableName)) {
                throw new VariableAlreadyDefinedException(variableName);
            }
            for (Object o : ((Tuple) tuple).getElements()) {
                //TODO remove lines 33-36 if 38-40 dont cause problems;
            /*HashMap<String, Object> nestedHashmap = new HashMap<>(interpretation.getEnvironment());
            Interpretation nestedInterpretation = new Interpretation(nestedHashmap);
            nestedInterpretation.getEnvironment().put(variableName, o);
            Boolean result = (Boolean) getRightNode().evaluateNode(nestedInterpretation);*/

                interpretation.getEnvironment().put(variableName, o);
                Object result = getRightNode().evaluateNode(interpretation);
                interpretation.getEnvironment().remove(variableName);
                if(result instanceof Boolean) {
                    if ((Boolean) result) {
                        return true;
                    }
                } else {
                    throw new IllegalArgumentException("Expected Boolean but got " + result.getClass().getSimpleName());
                }
            }
            return false;
        } else {
            throw new IllegalArgumentException("Expected Tuple but got: " + tuple.getClass().getSimpleName());
        }
    }
}
