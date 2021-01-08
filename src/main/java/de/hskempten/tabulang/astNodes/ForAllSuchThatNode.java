package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ForAllSuchThatNode extends BinaryPredicateNode {
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
        if (interpretation.getEnvironment().containsKey(variableName)) {
            throw new VariableAlreadyDefinedException(variableName);
        }
        Object o = getLeftNode().evaluateNode(interpretation);
        o = ifTupleTransform(o);
        if (!(o instanceof Tuple<?>) && !(o instanceof Table)) {
            throw new IllegalTupleOperandArgumentException(getTextPosition(), o.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());
        }

        if(o instanceof Tuple<?> tuple) {
            for (Object object : tuple.getElements()) {
                InternalBoolean booleanResult = insertVariableAndEvaluate(object, variableName, interpretation);
                if (!booleanResult.getaBoolean()) {
                    return new InternalBoolean(false);
                }
            }
        } else {
            for (Object object : ((Table<?>) o).getRows()) {
                InternalBoolean booleanResult = insertVariableAndEvaluate(object, variableName, interpretation);
                if(!booleanResult.getaBoolean()){
                    return new InternalBoolean(false);
                }
            }
        }
        return new InternalBoolean(true);
    }

    @Override
    public String toString() {
        return "forAll " + variableName + " in " + getLeftNode() + " such that " + getRightNode();
    }
}
