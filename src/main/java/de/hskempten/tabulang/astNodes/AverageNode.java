package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AverageNode<E> extends BinaryNode{
    public AverageNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        BigDecimal sum = BigDecimal.ZERO;
        String columnIdentifier = (String) getLeftNode().evaluateNode(interpretation);
        Table<E> table = (Table<E>) getRightNode().evaluateNode(interpretation);
        BigDecimal numberElements = BigDecimal.ZERO;
        for(ArrayList<E> a : table.getTuples()){
            System.out.println("......");
            System.out.println(a);
            BigDecimal columnValue = (BigDecimal) a.get(table.getColumnIndex(columnIdentifier));
            sum = sum.add(columnValue);
            numberElements = numberElements.add(BigDecimal.ONE);

        }
        return sum.divide(numberElements);
    }
}
