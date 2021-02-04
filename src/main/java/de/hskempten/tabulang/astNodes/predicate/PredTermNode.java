package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class PredTermNode extends PredicateNode {
    private TermNode term;

    public PredTermNode(TermNode term, TextPosition textPosition) {
        super(textPosition);
        this.setTerm(term);
    }

    public TermNode getTerm() {
        return term;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    /**
     * Checks if node evaluates to a boolean.
     *
     * @return InternalBoolean with value of evaluated node.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean internalBoolean = term.verifyAndReturnBoolean(interpretation);
        return internalBoolean;
    }
}
