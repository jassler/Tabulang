package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.FlipTItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class FlipTType implements LanguageType {

    public static FlipTType instance = new FlipTType();

    @Override
    public FlipTItem parse(Lexer l) throws ParseTimeException {
        FlipTItem item;

        String myString; //"horizontal"/"vertical"
        TermItem myTerm;

        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);

        item = new FlipTItem(myString, myTerm);

        return item;
    }
}
