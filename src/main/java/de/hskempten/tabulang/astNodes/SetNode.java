package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class SetNode extends StatementNode {
    private TermNode node;
    private int mapVal;

    public SetNode(TermNode node, int mapVal, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
        this.mapVal = mapVal;
    }

    public TermNode getNode() {
        return node;
    }

    public void setNode(TermNode node) {
        this.node = node;
    }

    public int getMapVal() {
        return mapVal;
    }

    public void setMapVal(int mapVal) {
        this.mapVal = mapVal;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = getNode().evaluateNode(interpretation);
        interpretation.setNestingLevel(mapVal);
        interpretation.getEnvironment().put("mapValue" + mapVal, o);
        return o;
    }

    @Override
    public String toString() {
        return "set " + node;
    }
}
