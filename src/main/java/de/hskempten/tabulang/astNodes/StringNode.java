package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class StringNode extends TermNode{
    private String string;

    public StringNode(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String evaluateNode(Interpretation interpretation) {
        return string;
    }

    @Override
    public String toString() {
        return "StringNode{" +
                "string='" + string + '\'' +
                "} ";
    }
}
