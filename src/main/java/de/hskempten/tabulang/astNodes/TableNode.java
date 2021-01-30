package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Styleable;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class TableNode<E extends Styleable> extends TermNode {
    private Table<E> table;

    public TableNode(Table<E> table) {
        this.table = table;
    }

    public Table<E> getTable() {
        return table;
    }

    public void setTable(Table<E> table) {
        this.table = table;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        for (var tuple : table) {
            System.out.println(tuple);
            int i = 0;
            for (int j = 0; j < tuple.size(); j++) {
                tuple.setToIndex(j, (E) (((Node) tuple.getFromIndex(j)).evaluateNode(interpretation)));
            }
        }
        return table;
    }

    @Override
    public String toString() {
        return table.toString();
    }
}
