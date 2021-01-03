package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class UniteNode extends BinaryTermNode{
    public UniteNode(Node leftNode, Node rightNode) {
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
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") unite " + right + " (" + right.getClass() + ")' can not be executed. " +
                    "Allowed operands: Tables.");
        }
        return ((Table) left).union((Table) right);
    }
}
