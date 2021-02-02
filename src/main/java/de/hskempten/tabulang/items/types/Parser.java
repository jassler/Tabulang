package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.LanguageItemAbstract;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public interface Parser {

    /**
     * Checks the tokens at the current lexer position and matches them with the language syntax.
     * When a new syntax element starts, the parse method of its SyntaxType is called.
     *
     * @param l Instance from Lexer
     * @return An object of the corresponding syntax type
     * @throws ParseTimeException
     */
    LanguageItemAbstract parse(Lexer l) throws ParseTimeException;

}
