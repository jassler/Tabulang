package de.hskempten.tabulang.astNodes.tupleOperations;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class TupleElementNode extends BinaryTermNode {


    public TupleElementNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Creates new Tuple/Table with columns from specified Tuple/Table.
     * Can either be a single column from Tuple/Table T (e.g. T.'x') or multiple columns (e.g. T.[x,y]).
     *
     * @return new Tuple if input was a Tuple, new Table if input was a Table.
     * @throws InterpreterException the column identfier is neither a String (e.g. 'x') nor a Tuple (e.g. [x,y]).
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        TupleOperation<?> left = getLeftNode().verifyAndReturnTupleOperation(interpretation);

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
