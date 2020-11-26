package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.LanguageItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public interface Parser {

    public LanguageItem parse(Lexer l) throws ParseTimeException;

}
