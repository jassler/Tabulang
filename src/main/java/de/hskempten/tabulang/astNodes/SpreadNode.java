package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SpreadNode extends BinaryTermNode{
    public SpreadNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if(left instanceof BigDecimal && right instanceof BigDecimal) {
            if(((BigDecimal) left).compareTo((BigDecimal) right) <= 0) {
                ArrayList<Integer> a = new ArrayList<>();
                ArrayList<String> s = new ArrayList<>();
                Integer name = 0;
                for (Integer j = ((BigDecimal) left).intValue(); j <= ((BigDecimal) right).intValue(); j++) {
                    a.add(j);
                    s.add(name.toString());
                    name += 1;
                }
                String[] names = new String[a.size()];
                s.toArray(names);
                Integer[] array = new Integer[a.size()];
                a.toArray(array);
                return new Tuple(array, names, true);
            } else {
                throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") ... " + right + " (" + right.getClass() + ") can not be executed. " +
                        "Left value has to be less than or equal to right value.");
            }
        } else {
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") ... " + right + " (" + right.getClass() + ") can not be executed. " +
                    "Allowed operands: Numbers.");
        }
    }
}
