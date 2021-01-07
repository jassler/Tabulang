package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.Arrays;

public class DistinctFromNode extends TermNode {
    private TermNode node;
    private IdentifierNode[] names;

    public DistinctFromNode(TermNode node, ArrayList<IdentifierNode> names, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
        this.names = (IdentifierNode[]) names.toArray();
    }

    public Node getNode() {
        return node;
    }

    public void setNode(TermNode node) {
        this.node = node;
    }

    public IdentifierNode[] getNames() {
        return names;
    }

    public void setNames(IdentifierNode[] names) {
        this.names = names;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object object = node.evaluateNode(interpretation);
        try {
            object = ifTupleTransform(object);
        } catch (TupleCannotBeTransformedException ignored) {
            //TODO testen 4.01.
        }
        if (object instanceof Table) {
            ArrayList<InternalString> columnNames = new ArrayList<>(names.length);
            for (TermNode t : names) {
                columnNames.add((InternalString) t.evaluateNode(interpretation));
            }
            return ((Table<?>) object).projection(columnNames.toArray(InternalString[]::new));
        } else {
            throw new IllegalOperandArgumentException("Got " + object + " on the right side of'" + toString()
                    + "'. Allowed operand on the right side: Table.");
        }
    }

    @Override
    public String toString() {
        return "distinct " + Arrays.toString(names) + " from " + node;
    }
}
