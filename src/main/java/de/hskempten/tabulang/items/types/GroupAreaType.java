package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.GroupAreaItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class GroupAreaType implements Parser {

    public static GroupAreaType instance = new GroupAreaType();

    @Override
    public GroupAreaItem parse(Lexer l) throws ParseTimeException {
        GroupAreaItem item;

        //"before"/"after"
        String myString;

        TextPosition startP = l.lookahead().getPosition();
        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.KEYWORD);

        item = new GroupAreaItem(myString);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
