package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.FlipTItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class FlipTType implements LanguageType {

    public static FlipTType instance = new FlipTType();

    @Override
    public FlipTItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
