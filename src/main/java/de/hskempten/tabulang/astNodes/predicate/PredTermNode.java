package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.Interpretation;
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

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object termObject = term.evaluateNode(interpretation);
        if (!(termObject instanceof InternalBoolean internalBoolean)) {
            throw new IllegalBooleanArgumentException(getTextPosition(), termObject.getClass().getSimpleName(), term.getTextPosition().getContent());
        }
        return internalBoolean;
    }
}
