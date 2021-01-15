package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.LanguageItemAbstract;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public interface Parser {

    public LanguageItemAbstract parse(Lexer l) throws ParseTimeException;

}
