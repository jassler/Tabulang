package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class BooleanNode extends PredicateNode {
    private Boolean aBoolean;

    public BooleanNode(Boolean aBoolean) {
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
        return aBoolean;
    }
}
