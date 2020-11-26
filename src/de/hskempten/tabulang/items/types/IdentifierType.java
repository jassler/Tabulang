package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class IdentifierType implements Parser {

    public static IdentifierType instance = new IdentifierType();

    @Override
    public IdentifierItem parse(Lexer l) throws ParseTimeException {

        IdentifierItem item;

        String myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.VARIABLE);
        item = new IdentifierItem(myString);

        return item;
    }
}
