package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.BinBoolItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class BinBoolType implements LanguageType {

    public static BinBoolType instance = new BinBoolType();

    @Override
    public BinBoolItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
