package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.MarkStmntItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.SetStmntItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class MarkStmntType implements LanguageType {

    public static MarkStmntType instance = new MarkStmntType();

    @Override
    public MarkStmntItem parse(Lexer l) throws ParseTimeException {
        MarkStmntItem item;

        TermItem myTerm;
        TermItem mySecondTerm;
        PredItem myPred;

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);
        if (!"as".equals(l.lookahead().getContent())){
            throw new ParseTimeException(l, "Illegal keyword. Expected 'as', got: " + l.lookahead().getContent());
        }
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        mySecondTerm = TermType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType())){
            if(!"if".equals(l.lookahead().getContent())){
                throw new ParseTimeException(l, "Illegal keyword. Expected 'if', got: " + l.lookahead().getContent());
            }
            l.getNextTokenAndExpect(TokenType.KEYWORD);
            myPred = PredType.instance.parse(l);

            item = new MarkStmntItem(myTerm, mySecondTerm, myPred);
        }else{
            item = new MarkStmntItem(myTerm,mySecondTerm);
        }

        return item;
    }
}
