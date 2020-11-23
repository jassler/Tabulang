package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.ForallPredItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class ForallPredType implements LanguageType {

    public static ForallPredType instance = new ForallPredType();

    @Override
    public ForallPredItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
