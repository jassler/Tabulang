package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class NullNode extends TermNode {
    public NullNode(TextPosition textPosition) {
        super(textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }

    @Override
    public String toString() {
        return "null";
    }
}
