package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
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
        if(left instanceof InternalNumber && right instanceof InternalNumber) {
            if(((InternalNumber) left).compareTo((InternalNumber) right) <= 0) {
                ArrayList<Integer> a = new ArrayList<>();
                ArrayList<String> s = new ArrayList<>();
                int name = 0;
                Object leftValue = ((InternalNumber) left).getValue();
                Object rightValue = ((InternalNumber) right).getValue();
                if (leftValue instanceof Integer && rightValue instanceof Integer){
                    for (int j = (int) leftValue; j <= (int) rightValue; j++) {
                        a.add(j);
                        s.add(Integer.toString(name));
                        name += 1;
                    }
                    String[] names = new String[a.size()];
                    s.toArray(names);
                    Integer[] array = new Integer[a.size()];
                    a.toArray(array);
                    return new Tuple(array, names, true);
                } else {
                    throw new IllegalOperandArgumentException("Operation '" + leftValue + " ... " + rightValue + "' can not be executed. " +
                            "Operands need to be integer.");
                }
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
