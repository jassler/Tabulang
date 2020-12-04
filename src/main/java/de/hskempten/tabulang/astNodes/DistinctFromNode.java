package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class DistinctFromNode extends Node{
    private Node node;
    private String[] columnNames;

    public DistinctFromNode(Node node, String[] columnNames) {
        super(NodeType.NODE);
        this.node = node;
        this.columnNames = columnNames;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        return ((Table) o).projection(columnNames);
    }
}
