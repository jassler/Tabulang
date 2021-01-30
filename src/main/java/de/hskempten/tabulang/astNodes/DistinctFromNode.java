package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTableOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class DistinctFromNode extends TermNode {
    private TermNode node;
    private ArrayList<IdentifierNode> names;

    public DistinctFromNode(TermNode node, ArrayList<IdentifierNode> names, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
        this.names = names;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(TermNode node) {
        this.node = node;
    }

    public ArrayList<IdentifierNode> getNames() {
        return names;
    }

    public void setNames(ArrayList<IdentifierNode> names) {
        this.names = names;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object object = node.evaluateNode(interpretation);
        object = ifTupleTransform(object);

        if (object instanceof Table table) {
            ArrayList<InternalString> columnNames = new ArrayList<>();
            for (IdentifierNode identifier : names) {
                columnNames.add((InternalString) identifier.evaluateNode(interpretation));
            }
            return table.projection(columnNames.toArray(InternalString[]::new));
        } else {
            throw new IllegalTableOperandArgumentException(getTextPosition(), object.getClass().getSimpleName(), node.getTextPosition().getContent());
        }
    }

    @Override
    public String toString() {
        return "distinct " + names + " from " + node;
    }
}
