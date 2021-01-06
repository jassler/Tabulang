package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IdentifierType implements Parser {

    public static IdentifierType instance = new IdentifierType();

    @Override
    public IdentifierItem parse(Lexer l) throws ParseTimeException {

        IdentifierItem item;

        TextPosition startP = l.lookahead().getPosition();
        String myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.VARIABLE);
        item = new IdentifierItem(myString);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
