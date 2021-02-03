package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class HorizontalTupleNode extends TermNode {
    private TermNode node;

    public HorizontalTupleNode(TermNode tupleNode, TextPosition textPosition) {
        super(textPosition);
        this.node = tupleNode;
    }

    /**
     * Creates new Tuple/Table of the specified Tuple/Table but is transposed horizontally.
     *
     * @return new Tuple/Table that is equal to the specified Tuple/Table but transposed horizontally.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        TupleOperation<?> tupleOperation = node.verifyAndReturnTupleOperation(interpretation);
        TupleOperation<?> result = tupleOperation.clone();
        result.setHorizontal(true);
        return result;
    }

    @Override
    public String toString() {
        return "horizontal " + node;
    }
}
