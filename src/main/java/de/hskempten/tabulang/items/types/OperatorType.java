package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.OperatorItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class OperatorType implements Parser {

    public static OperatorType instance = new OperatorType();

    @Override
    public OperatorItem parse(Lexer l) throws ParseTimeException {
        OperatorItem item;

        //+ - * / div mod ^
        String myString;

        TextPosition startP = l.lookahead().getPosition();
        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.BINARY_OPERATOR);
        item = new OperatorItem(myString);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
