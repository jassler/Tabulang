package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IntersectNode extends BinaryTermNode{
    public IntersectNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        try {
            left = checkIfTable(left);
            right = checkIfTable(right);
        } catch (TupleCannotBeTransformedException | IllegalArgumentException runtimeException){
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") intersect " + right + " (" + right.getClass() + ")' can not be executed. " +
                    "Allowed operands: Tables.");
        }
        return ((Table) left).intersection((Table) right);
    }
}
