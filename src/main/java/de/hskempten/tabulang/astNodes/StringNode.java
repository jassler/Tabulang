package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class StringNode extends Node{
    private String string;

    public StringNode(String string) {
        super(NodeType.STRING);
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String evaluateNode(Interpretation i) {
        return string;
    }
}
