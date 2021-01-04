package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Styleable;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterNode extends BinaryTermNode{
    public FilterNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object object = getLeftNode().evaluateNode(interpretation);
        try {
            object = ifTupleTransform(object);
        } catch (TupleCannotBeTransformedException transformedException){
            throw new IllegalOperandArgumentException("Got " + object + " (" + object.getClass() + ") on the left side of the 'filter' keyword." +
                    "Allowed operand on the left side: Table.");
        }

        if(!(object instanceof Table<?> table))
            throw new IllegalArgumentException("Expected Table but got " + object.getClass().getSimpleName());

        ArrayList<String> colNames = new ArrayList<>(table.getColNames().getNames());

        return table.filter(tuple -> {
            Interpretation nested = interpretation.deepCopy();
            for (int j = 0; j < tuple.size(); j++) {
                Object element = tuple.getFromIndex(j);
                Object name = colNames.get(j);
                nested.getEnvironment().put(name.toString(), element);
            }

            Object result = getRightNode().evaluateNode(nested);
            if (!(result instanceof InternalBoolean booleanResult)) {
                throw new IllegalBooleanOperandArgumentException(toString());
            }
            return (boolean) result;
        });
    }

    @Override
    public String toString() {
        return getLeftNode() + " filter " + getRightNode();
    }
}
