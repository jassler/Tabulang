package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.PQuantifiedItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class PQuantifiedType implements LanguageType {

    public static PQuantifiedType instance = new PQuantifiedType();

    @Override
    public PQuantifiedItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
