package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.AnyStatementItem;
import de.hskempten.tabulang.items.IfStmntItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IfStmntType implements Parser {

    public static IfStmntType instance = new IfStmntType();

    @Override
    public IfStmntItem parse(Lexer l) throws ParseTimeException {
        IfStmntItem item;

        //'if'
        PredItem myPred;
        AnyStatementItem myAnyStatement;
        //'else'
        AnyStatementItem myOptionalAnyStatement;

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myPred = PredType.instance.parse(l);
        myAnyStatement = AnyStatementType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType()) && "else".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
            myOptionalAnyStatement = AnyStatementType.instance.parse(l);
            item = new IfStmntItem(myPred, myAnyStatement, myOptionalAnyStatement);
        } else {
            item = new IfStmntItem(myPred, myAnyStatement);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
