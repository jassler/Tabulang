package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Iterator;

public class ForAllSuchThatNode extends BinaryPredicateNode {
    private String variableName;

    public ForAllSuchThatNode(Node leftNode, Node rightNode, String variableName, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
        this.variableName = variableName;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if (interpretation.getEnvironment().containsKey(variableName)) {
            throw new VariableAlreadyDefinedException(variableName);
        }
        Object o = getLeftNode().evaluateNode(interpretation);
        o = ifTupleTransform(o);
        if (!(o instanceof TupleOperation<?> tupleOperation)) {
            throw new IllegalTupleOperandArgumentException(getTextPosition(), o.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());
        }
        Iterator it = tupleOperation.iterator();
        while(it.hasNext()){
            Object object = it.next();
            InternalBoolean booleanResult = insertVariableAndEvaluate(object, variableName, interpretation);
            if(!booleanResult.getaBoolean()){
                return new InternalBoolean(false);
            }
        }
        return new InternalBoolean(true);
    }

    @Override
    public String toString() {
        return "forAll " + variableName + " in " + getLeftNode() + " such that " + getRightNode();
    }
}
