package de.hskempten.tabulang.astNodes.tableOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.function.Predicate;

public class FilterNode extends BinaryTermNode {
    public FilterNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Creates a new Table with rows from given Table that fulfill a specified predicate.
     * See {@link Table#filter(Predicate)}
     *
     * @return Table object with rows from given Table that fulfill the predicate.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table<?> table = getLeftNode().verifyAndReturnTable(interpretation);

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
                throw new IllegalBooleanArgumentException(toString());
            }
            return booleanResult.getBoolean();
        });
    }

    @Override
    public String toString() {
        return getLeftNode() + " filter " + getRightNode();
    }
}
