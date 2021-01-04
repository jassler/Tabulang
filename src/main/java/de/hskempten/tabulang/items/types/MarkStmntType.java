package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.MarkStmntItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;
import de.hskempten.tabulang.types.LanguageType;

public class MarkStmntType implements LanguageType {

    public static MarkStmntType instance = new MarkStmntType();

    @Override
    public MarkStmntItem parse(Lexer l) throws ParseTimeException {
        MarkStmntItem item;

        TermItem myTerm;
        TermItem mySecondTerm;
        PredItem myPred;

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);
        if (!"as".equals(l.lookahead().getContent())) {
            throw new ParseTimeException(l, "Illegal keyword. Expected 'as', got: " + l.lookahead().getContent());
        }
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        mySecondTerm = TermType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType())) {
            if (!"if".equals(l.lookahead().getContent())) {
                throw new ParseTimeException(l, "Illegal keyword. Expected 'if', got: " + l.lookahead().getContent());
            }
            l.getNextTokenAndExpect(TokenType.KEYWORD);
            myPred = PredType.instance.parse(l);

            item = new MarkStmntItem(myTerm, mySecondTerm, myPred);
        } else {
            item = new MarkStmntItem(myTerm, mySecondTerm);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
