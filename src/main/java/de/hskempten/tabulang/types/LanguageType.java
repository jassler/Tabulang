package de.hskempten.tabulang.types;

import de.hskempten.tabulang.items.LanguageItemAbstract;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public interface LanguageType {

    public LanguageItemAbstract parse(Lexer l) throws ParseTimeException;
}
