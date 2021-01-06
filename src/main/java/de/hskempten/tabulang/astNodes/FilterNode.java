package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class FilterNode extends BinaryTermNode{
    public FilterNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
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

        ArrayList<InternalString> colNames = new ArrayList<>(table.getColNames().getNames());

        return table.filter(tuple -> {
            Interpretation nested = interpretation.deepCopy();

            for (int j = 0; j < tuple.size(); j++) {
                Object element = tuple.getFromIndex(j);
                InternalString name = colNames.get(j);
                nested.getEnvironment().put(name.getString(), element);
            }

            nested.putValue("mapvalue", tuple);

            Object result = getRightNode().evaluateNode(nested);
            if (!(result instanceof InternalBoolean booleanResult)) {
                throw new IllegalBooleanOperandArgumentException(toString());
            }
            return booleanResult.getaBoolean();
        });
    }

    @Override
    public String toString() {
        return getLeftNode() + " filter " + getRightNode();
    }
}
