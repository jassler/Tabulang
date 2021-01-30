package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class HorizontalTupleNode extends TermNode {
    private TermNode node;

    public HorizontalTupleNode(TermNode tupleNode, TextPosition textPosition) {
        super(textPosition);
        this.node = tupleNode;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);

        if(!(o instanceof TupleOperation<?> t)) {
            throw new IllegalTupleOperandArgumentException(getTextPosition(), o.getClass().getSimpleName(), node.getTextPosition().getContent());
        }
        TupleOperation<?> result = t.clone();
        result.setHorizontal(true);
        return result;
    }

    @Override
    public String toString() {
        return "horizontal " + node;
    }
}
