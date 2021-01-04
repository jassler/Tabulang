package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.BinRelSymItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;
import de.hskempten.tabulang.types.LanguageType;

public class BinRelSymType implements LanguageType {

    public static BinRelSymType instance = new BinRelSymType();

    @Override
    public BinRelSymItem parse(Lexer l) throws ParseTimeException {
        BinRelSymItem item;

        String myString;

        TextPosition startP = l.lookahead().getPosition();
        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.BINRELSYM);

        item = new BinRelSymItem(myString);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
