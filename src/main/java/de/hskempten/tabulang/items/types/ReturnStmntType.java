package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.ReturnStmntItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ReturnStmntType implements Parser {

    public static ReturnStmntType instance = new ReturnStmntType();

    @Override
    public ReturnStmntItem parse(Lexer l) throws ParseTimeException {
        ReturnStmntItem item;

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        TermItem myTerm = TermType.instance.parse(l);
        l.getNextTokenAndExpect(TokenType.SEMICOLON);

        item = new ReturnStmntItem(myTerm);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
