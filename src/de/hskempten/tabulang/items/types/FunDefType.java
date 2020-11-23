package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.FunDefItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class FunDefType implements Parser {

    public static FunDefType instance = new FunDefType();

    @Override
    public FunDefItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
