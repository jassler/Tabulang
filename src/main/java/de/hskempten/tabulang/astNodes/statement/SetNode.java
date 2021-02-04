package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
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

    /**
     * Assigns specified value to the key 'mapValue' in the interpretation.
     * Also sets the nesting level of the interpretation to the value given by the parser.
     *
     * @return value that got assigned to 'mapValue'.
     */
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
