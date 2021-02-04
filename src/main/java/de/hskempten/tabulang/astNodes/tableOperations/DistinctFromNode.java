package de.hskempten.tabulang.astNodes.tableOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
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

    /**
     * Creates a projection with specified columns on given Table.
     * See {@link Table#projection(InternalString...)}
     *
     * @return Table object with specified columns from given Table.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table table = node.verifyAndReturnTable(interpretation);
        ArrayList<InternalString> columnNames = new ArrayList<>();
        for (IdentifierNode identifier : names) {
            columnNames.add((InternalString) identifier.evaluateNode(interpretation));
        }
        return table.projection(columnNames.toArray(InternalString[]::new));

    }

    @Override
    public String toString() {
        return "distinct " + names + " from " + node;
    }
}
