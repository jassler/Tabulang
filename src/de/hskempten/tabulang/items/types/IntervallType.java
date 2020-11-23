package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.IntervallItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class IntervallType implements Parser {

    public static IntervallType instance = new IntervallType();

    @Override
    public IntervallItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
