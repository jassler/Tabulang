package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class LoopStmntType implements Parser {

    public static LoopStmntType instance = new LoopStmntType();

    @Override
    public LoopStmntItem parse(Lexer l) throws ParseTimeException {
        LoopStmntItem item;

        StatementItem myStatement;
        SetStmntItem mySetStmnt;
        GroupStmntItem myGroupStmnt;
        MarkStmntItem myMarkStmnt; //';'

        TextPosition startP = l.lookahead().getPosition();
        switch (l.lookahead().getContent()) {
            case "set" -> {
                mySetStmnt = SetStmntType.instance.parse(l);
                item = new LoopStmntItem(mySetStmnt);
            }
            case "hiding", "group" -> {
                myGroupStmnt = GroupStmntType.instance.parse(l);
                item = new LoopStmntItem((myGroupStmnt));
            }
            case "mark" -> {
                myMarkStmnt = MarkStmntType.instance.parse(l);
                l.getNextTokenAndExpect(TokenType.SEMICOLON);
                item = new LoopStmntItem(myMarkStmnt);
            }
            default -> {
                myStatement = StatementType.instance.parse(l);
                item = new LoopStmntItem(myStatement);
            }
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;

    }
}
