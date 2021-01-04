package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.Objects;

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
    public Object evaluateNode(Interpretation interpretation) {
        return new InternalString(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringNode)) return false;
        StringNode that = (StringNode) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

    @Override
    public String toString() {
        return string;
    }
}
