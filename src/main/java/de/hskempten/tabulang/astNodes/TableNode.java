package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class TableNode<E> extends TermNode{
    private Table<E> table;

    public TableNode(Table table) {
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
        for(ArrayList<E> a : table.getRows()){
            System.out.println(a);
            int i = 0;
            for(int j = 0; j < a.size(); j++) {
                a.set(j, (E) ((Node) a.get(j)).evaluateNode(interpretation));
            }
            System.out.println("......");
        }
        System.out.println("--------");
        return table;
    }
}
