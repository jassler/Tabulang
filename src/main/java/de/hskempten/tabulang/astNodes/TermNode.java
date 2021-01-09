package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class TermNode extends Node {

    public TermNode() {
    }

    public TermNode(TextPosition textPosition) {
        super(textPosition);
    }


}
