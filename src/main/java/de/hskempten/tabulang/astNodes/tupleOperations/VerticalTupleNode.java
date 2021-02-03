package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class VerticalTupleNode extends TermNode {
    private TermNode node;

    public VerticalTupleNode(TermNode tupleNode, TextPosition textPosition) {
        super(textPosition);
        this.node = tupleNode;
    }

    /**
     * Creates new Tuple/Table of the specified Tuple/Table but is transposed vertically.
     *
     * @return new Tuple/Table that is equal to the specified Tuple/Table but transposed vertically.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        TupleOperation<?> tupleOperation = node.verifyAndReturnTupleOperation(interpretation);
        TupleOperation<?> result = tupleOperation.clone();
        result.setHorizontal(false);
        return result;
    }

    @Override
    public String toString() {
        return "vertical " + node;
    }
}
