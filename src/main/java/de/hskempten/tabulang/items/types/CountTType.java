package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.CountTItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;
import de.hskempten.tabulang.types.LanguageType;

public class CountTType implements LanguageType {

    public static CountTType instance = new CountTType();

    @Override
    public CountTItem parse(Lexer l) throws ParseTimeException {
        CountTItem item;

        //"horizontal"/"vertical"
        String myString;
        TermItem myTerm;

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        if ("keyword".equals(l.lookahead().getType()) && ("horizontal".equals(l.lookahead().getContent()) || "vertical".equals(l.lookahead().getContent()))) {
            myString = l.lookahead().getContent();
            l.getNextTokenAndExpect(TokenType.KEYWORD);
            myTerm = TermType.instance.parse(l);
            item = new CountTItem(myString, myTerm);
        } else {
            myTerm = TermType.instance.parse(l);
            item = new CountTItem(myTerm);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
