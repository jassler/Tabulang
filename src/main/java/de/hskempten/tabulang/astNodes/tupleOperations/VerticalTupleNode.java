package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleArgumentException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class VerticalTupleNode extends TermNode {
    private TermNode node;

    public VerticalTupleNode(TermNode tupleNode, TextPosition textPosition) {
        super(textPosition);
        this.node = tupleNode;
    }

    public TermNode getTupleNode() {
        return node;
    }

    public void setTupleNode(TermNode tupleNode) {
        this.node = tupleNode;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);

        if (!(o instanceof TupleOperation<?> t)) {
            throw new IllegalTupleArgumentException(getTextPosition(), o.getClass().getSimpleName(), node.getTextPosition().getContent());
        }
        TupleOperation<?> result = t.clone();
        result.setHorizontal(false);
        return result;
    }

    @Override
    public String toString() {
        return "vertical " + node;
    }
}