package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTableOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class FilterNode extends BinaryTermNode {
    public FilterNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object object = getLeftNode().evaluateNode(interpretation);
        object = ifTupleTransform(object);
        if (!(object instanceof Table<?> table))
            throw new IllegalTableOperandArgumentException(getTextPosition(), object.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent());

        ArrayList<InternalString> colNames = new ArrayList<>(table.getColNames().getNames());

        return table.filter(tuple -> {
            Interpretation nested = interpretation.deepCopy();

            for (int j = 0; j < tuple.size(); j++) {
                Object element = tuple.getFromIndex(j);
                InternalString name = colNames.get(j);
                nested.getEnvironment().put(name.getString(), element);
            }

            Object result = getRightNode().evaluateNode(nested);
            if (!(result instanceof InternalBoolean booleanResult)) {
                throw new IllegalBooleanOperandArgumentException(toString());
            }
            return booleanResult.getBoolean();
        });
    }

    @Override
    public String toString() {
        return getLeftNode() + " filter " + getRightNode();
    }
}
