package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.ExistsPredItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class ExistsPredType implements Parser {

    public static ExistsPredType instance = new ExistsPredType();

    @Override
    public ExistsPredItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
