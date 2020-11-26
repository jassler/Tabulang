package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.OperatorItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class OperatorType implements Parser {

    public static OperatorType instance = new OperatorType();

    @Override
    public OperatorItem parse(Lexer l) throws ParseTimeException {
        OperatorItem item;

        //+ - * / div mod ^
        String myString;

        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.BINARY_OPERATOR);
        item = new OperatorItem(myString);

        return item;
    }
}
