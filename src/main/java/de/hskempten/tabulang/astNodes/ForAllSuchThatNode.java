package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ForAllSuchThatNode extends BinaryPredicateNode{
    private String variableName;

    public ForAllSuchThatNode(Node leftNode, Node rightNode, String variableName, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
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
            if(result instanceof InternalBoolean booleanResult) {
                if (!booleanResult.getaBoolean()) {
                    interpretation.getEnvironment().remove(variableName);
                    return false;
                }
            }
        }
        interpretation.getEnvironment().remove(variableName);
        return true;
    }

    @Override
    public String toString() {
        return "forAll " + variableName + " in " + getLeftNode() + " such that " + getRightNode();
    }
}
