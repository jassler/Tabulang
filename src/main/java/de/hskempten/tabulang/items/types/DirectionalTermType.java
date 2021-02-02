package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.DirectionalTermItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class DirectionalTermType implements Parser {

    public static DirectionalTermType instance = new DirectionalTermType();

    @Override
    public DirectionalTermItem parse(Lexer l) throws ParseTimeException {
        DirectionalTermItem item;

        String myString;
        TermItem myTerm;

        TextPosition startP = l.lookahead().getPosition();
        if ("keyword".equals(l.lookahead().getType()) && ("horizontal".equals(l.lookahead().getContent()) || "vertical".equals(l.lookahead().getContent()))) {
            myString = l.lookahead().getContent();
            l.getNextToken();
            myTerm = TermType.instance.parse(l);
            item = new DirectionalTermItem(myString, myTerm);
        } else {
            myTerm = TermType.instance.parse(l);
            item = new DirectionalTermItem(myTerm);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
