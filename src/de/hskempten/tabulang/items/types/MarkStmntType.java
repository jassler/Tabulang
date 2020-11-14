package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.MarkStmntItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class MarkStmntType implements LanguageType {

    public static MarkStmntType instance = new MarkStmntType();

    @Override
    public MarkStmntItem parse(Lexer l) throws ParseTimeException {
        throw new ParseTimeException(l, "Not yet implemented");
    }
}
