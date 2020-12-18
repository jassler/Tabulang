package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class DistinctFromNode extends TermNode{
    private Node node;
    private IdentifierNode[] names;

    public DistinctFromNode(Node node, ArrayList<IdentifierNode> names) {
        this.node = node;
        this.names = (IdentifierNode[]) names.toArray();
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
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
        Object table = node.evaluateNode(interpretation);
        if (table instanceof Table) {
            ArrayList<String> columnNames = new ArrayList<>();
            for (TermNode t: names) {
                Object o = t.evaluateNode(interpretation);
                if(o instanceof String){
                    columnNames.add((String) o);
                } else {
                    throw new IllegalArgumentException("Expected String but got " + o.getClass().getSimpleName());
                }
            }
            return ((Table) table).projection((String[]) columnNames.toArray());
        } else {
            throw new IllegalArgumentException("Expected Table but got " + table.getClass().getSimpleName());
        }
    }
}
