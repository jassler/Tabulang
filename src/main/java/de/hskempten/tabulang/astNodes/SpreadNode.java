package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SpreadNode extends BinaryNode{
    public SpreadNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        BigDecimal left = (BigDecimal) getLeftNode().evaluateNode(interpretation);
        BigDecimal right = (BigDecimal) getRightNode().evaluateNode(interpretation);
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        Integer name = 0;
        for(Integer j = left.intValue(); j <= right.intValue() ; j++){
            a.add(j);
            s.add(name.toString());
            name += 1;
        }
        System.out.println("Tupel: ");
        int k = 0;
        String[] names = new String[a.size()];
        s.toArray(names);
        Integer[] array = new Integer[a.size()];
        a.toArray(array);
        for(Integer b : array){
            System.out.print(b + " ");
            k++;
        }
        System.out.println("");
        System.out.println("Anzahl Elemente: " + k);
        for(String str : names){
            System.out.print(str + " ");
        }
        System.out.println("");
        Tuple result = new Tuple(array, names, true);
        return result;
    }
}
