package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.DirectionalTermItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class DirectionalTermType implements LanguageType {

    public static DirectionalTermType instance = new DirectionalTermType();

    @Override
    public DirectionalTermItem parse(Lexer l) throws ParseTimeException {
        DirectionalTermItem item;

        String myString;
        TermItem myTerm;

        if ("keyword".equals(l.lookahead().getType()) && ("horizontal".equals(l.lookahead().getContent()) || "vertical".equals(l.lookahead().getContent()))) {
            myString = l.lookahead().getContent();
            l.getNextToken();
            myTerm = TermType.instance.parse(l);
            item = new DirectionalTermItem(myString, myTerm);
        } else {
            myTerm = TermType.instance.parse(l);
            item = new DirectionalTermItem(myTerm);
        }

        return item;
    }
}
