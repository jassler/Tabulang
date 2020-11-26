package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.IntervallItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class IntervallType implements Parser {

    public static IntervallType instance = new IntervallType();

    @Override
    public IntervallItem parse(Lexer l) throws ParseTimeException {
        IntervallItem item;

        TermItem myTerm;
        TermItem mySecondTerm;

        myTerm = TermType.instance.parse(l);
        //l.getNextTokenAndExpect(TokenType.INTERVAL); //TODO create new TokenType or extend one
        mySecondTerm = TermType.instance.parse(l);

        item = new IntervallItem(myTerm, mySecondTerm);

        return item;
    }
}
