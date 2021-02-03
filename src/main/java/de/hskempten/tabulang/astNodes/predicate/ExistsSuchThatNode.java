package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Iterator;

public class ExistsSuchThatNode extends BinaryPredicateNode {
    private String variableName;

    public ExistsSuchThatNode(Node leftNode, Node rightNode, String variableName, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
        this.variableName = variableName;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if (interpretation.getEnvironment().containsKey(variableName)) {
            throw new VariableAlreadyDefinedException(variableName);
        }
        Object o = getLeftNode().evaluateNode(interpretation);
        if (!(o instanceof TupleOperation<?> tupleOperation)) {
            throw new IllegalTupleArgumentException(getTextPosition(), o.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());
        }

        for (Object object : tupleOperation) {
            InternalBoolean booleanResult = insertVariableAndEvaluate(object, variableName, interpretation);
            if (booleanResult.getBoolean()) {
                return new InternalBoolean(true);
            }
        }
        return new InternalBoolean(false);

    }

    @Override
    public String toString() {
        return "exists " + variableName + " in " + getLeftNode() + " such that " + getRightNode();
    }
}