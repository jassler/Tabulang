package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.LanguageItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class PredType implements LanguageType {

    public static PredType instance = new PredType();

    @Override
    public PredItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l,"Not yet implemented");
    }
}
