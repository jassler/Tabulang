package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class ArithmeticNode extends TermNode {

    public ArithmeticNode(TextPosition textPosition) {
        super(textPosition);
    }
}

