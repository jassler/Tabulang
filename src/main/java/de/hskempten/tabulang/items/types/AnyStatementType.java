package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class AnyStatementType implements Parser {

    public static AnyStatementType instance = new AnyStatementType();

    @Override
    public AnyStatementItem parse(Lexer l) throws ParseTimeException {
        AnyStatementItem item;

        StatementItem myStatement;
        ReturnStmntItem myReturnStmnt;
        SetStmntItem mySetStmnt;
        GroupStmntItem myGroupStmnt;

        TextPosition startP = l.lookahead().getPosition();
        if ("keyword".equals(l.lookahead().getType())) {
            switch (l.lookahead().getContent()) {
                case "return" -> {
                    myReturnStmnt = ReturnStmntType.instance.parse(l);
                    item = new AnyStatementItem(myReturnStmnt);
                }
                case "set" -> {
                    mySetStmnt = SetStmntType.instance.parse(l);
                    item = new AnyStatementItem(mySetStmnt);
                }
                case "hiding", "group" -> {
                    myGroupStmnt = GroupStmntType.instance.parse(l);
                    item = new AnyStatementItem(myGroupStmnt);
                }
                default -> {
                    myStatement = StatementType.instance.parse(l);
                    item = new AnyStatementItem(myStatement);
                }
            }
        } else {
            myStatement = StatementType.instance.parse(l);
            item = new AnyStatementItem(myStatement);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
