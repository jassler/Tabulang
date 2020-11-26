package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.BinBoolItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class BinBoolType implements LanguageType {

    public static BinBoolType instance = new BinBoolType();

    @Override
    public BinBoolItem parse(Lexer l) throws ParseTimeException {
        BinBoolItem item;

        //"and"/"or"/"xor"/"iff"/"impl"
        String myString;

        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.BINBOOL);

        item = new BinBoolItem(myString);

        return item;
    }
}
