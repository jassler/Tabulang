package de.hskempten.tabulang.astNodes.tupleOperations;


import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class TupleElementNode extends BinaryTermNode {


    public TupleElementNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if (!(left instanceof Tuple<?>) && !(left instanceof Table<?>)) {
            throw new IllegalTupleArgumentException(getTextPosition(), left.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent());
        }

        Object right = getRightNode().evaluateNode(interpretation);
        if (!(right instanceof InternalString) && !(right instanceof Tuple<?>)) {
            throw new InterpreterException(getTextPosition(), right.getClass().getSimpleName(), getRightNode().getTextPosition().getContent(), "Allowed operands: String or Tuple.");
        }

        if (left instanceof Tuple<?> tuple) {

            if (right instanceof InternalString colIdentifier) {
                return tuple.get(colIdentifier);
            } else {
                Tuple<?> tupleColIdentifier = (Tuple<?>) right;
                return tuple.projection(tupleColIdentifier.getElements().stream().map(v -> new InternalString(v.toString())).toArray(InternalString[]::new));
            }
        } else {
            Table<?> table = (Table<?>) left;

            if (right instanceof InternalString colIdentifier) {
                return table.projection(colIdentifier);
            } else {
                Tuple<?> tupleColIdentifier = (Tuple<?>) right;
                return table.projection(tupleColIdentifier.getElements().stream().map(v -> new InternalString(v.toString())).toArray(InternalString[]::new));
            }
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + "." + getRightNode();
    }
}
