package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.LoopItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class LoopType implements Parser {

    public static LoopType instance = new LoopType();

    public LoopItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
