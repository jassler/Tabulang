package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.BodyItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class BodyType implements Parser {

    public static BodyType instance = new BodyType();

    @Override
    public BodyItem parse(Lexer l) throws ParseTimeException {
        return null;
    }
}
