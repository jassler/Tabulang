package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableAlreadyDefinedException;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ExistsSuchThatNode extends BinaryPredicateNode {
    private String variableName;

    public ExistsSuchThatNode(Node leftNode, Node rightNode, String variableName, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
        this.variableName = variableName;
    }

    /**
     * Checks if tuple contains an element that lets an interpretation of a predicate evaluate to true.
     *
     * @return true if an element fulfills condition, false otherwise.
     * @throws VariableAlreadyDefinedException if variable name to be used is already used by interpretation.
     * @throws IllegalTupleArgumentException   if left node does not evaluate to a tuple/table.
     */
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
