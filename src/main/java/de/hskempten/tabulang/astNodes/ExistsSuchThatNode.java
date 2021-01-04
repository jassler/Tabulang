package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class ExistsSuchThatNode extends BinaryPredicateNode {
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
        if (!(tuple instanceof Tuple)) {
            throw new IllegalOperandArgumentException("Operation 'exists" + variableName + " in " + tuple + " (" + tuple.getClass() + ") can not be executed." +
                    tuple + " has to a a tuple.");
        }
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
            if (!(result instanceof Boolean)) {
                throw new IllegalArgumentException("Operation 'exists" + variableName + " in " + tuple + " (" + tuple.getClass() + ") such that " + getRightNode().toString() + " can not be finished." +
                        "The condition " + getRightNode().toString() + " returned a non-boolean value.");
            }
            if ((Boolean) result) {
                return true;
            }
        }
        return false;

    }
}
