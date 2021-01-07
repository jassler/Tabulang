package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.stream.Collectors;

public class TupleElementNode extends BinaryTermNode{


    public TupleElementNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);

        if(left instanceof Tuple<?> tuple) {
            Object right = getRightNode().evaluateNode(interpretation);

            if(right instanceof InternalString colIdentifier)
                return tuple.get(colIdentifier);

            else if(right instanceof Tuple<?> t)
                return tuple.projection(t.getElements().stream().map(v -> new InternalString(v.toString())).toArray(InternalString[]::new));

            else
                throw new IllegalArgumentException("Expected String but got: " + right.getClass().getSimpleName());
        }

        else if(left instanceof Table<?> table) {
            Object right = getRightNode().evaluateNode(interpretation);

            if(right instanceof InternalString colIdentifier)
                return table.projection(colIdentifier);

            else if(right instanceof Tuple<?> t)
                return table.projection((InternalString[]) t.getElements().stream().map(v -> new InternalString(v.toString())).toArray());

            else
                throw new IllegalArgumentException("Expected String or Tuple but got: " + right.getClass().getSimpleName());
        }

        else {
            throw new IllegalArgumentException("Expected Table or Tuple but got: " + left.getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + "." + getRightNode();
    }
}
