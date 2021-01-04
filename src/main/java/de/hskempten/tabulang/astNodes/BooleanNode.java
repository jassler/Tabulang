package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class BooleanNode extends PredicateNode {
    private Boolean aBoolean;

    public BooleanNode(Boolean aBoolean, TextPosition textPosition) {
        super(textPosition);
        this.aBoolean = aBoolean;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return new InternalBoolean(aBoolean);
    }

    @Override
    public String toString() {
        return aBoolean.toString();
    }
}
