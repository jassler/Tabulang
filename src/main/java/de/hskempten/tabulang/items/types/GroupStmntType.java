package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.FunCallItem;
import de.hskempten.tabulang.items.GroupAreaItem;
import de.hskempten.tabulang.items.GroupStmntItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class GroupStmntType implements Parser {

    public static GroupStmntType instance = new GroupStmntType();

    @Override
    public GroupStmntItem parse(Lexer l) throws ParseTimeException {
        GroupStmntItem item;

        String myString = null;
        GroupAreaItem myGroupArea = null;
        TermItem myTerm;
        FunCallItem myFunCall = null;

        TextPosition startP = l.lookahead().getPosition();
        if ("hiding".equals(l.lookahead().getContent())) {
            myString = l.lookbehind().getContent();
            l.getNextTokenAndExpect(TokenType.KEYWORD);
        }
        l.getNextTokenAndExpect(TokenType.KEYWORD);

        if ("keyword".equals(l.lookahead().getType()) && ("before".equals(l.lookahead().getContent()) || "after".equals(l.lookahead().getContent()))) {
            myGroupArea = GroupAreaType.instance.parse(l);
        }
        myTerm = TermType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType()) && "using".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
            myFunCall = FunCallType.instance.parse(l);
        }
        l.getNextTokenAndExpect(TokenType.SEMICOLON);

        if (myString == null && myGroupArea == null && myFunCall == null) {
            item = new GroupStmntItem(myTerm);
        } else if (myString == null && myGroupArea == null && myFunCall != null) {
            item = new GroupStmntItem(myTerm, myFunCall);
        } else if (myString == null && myGroupArea != null && myFunCall == null) {
            item = new GroupStmntItem(myGroupArea, myTerm);
        } else if (myString == null && myGroupArea != null && myFunCall != null) {
            item = new GroupStmntItem(myGroupArea, myTerm, myFunCall);
        } else if (myString != null && myGroupArea == null && myFunCall == null) {
            item = new GroupStmntItem(myString, myTerm);
        } else if (myString != null && myGroupArea == null && myFunCall != null) {
            item = new GroupStmntItem(myString, myTerm, myFunCall);
        } else if (myString != null && myGroupArea != null && myFunCall == null) {
            item = new GroupStmntItem(myString, myGroupArea, myTerm);
        } else {
            item = new GroupStmntItem(myString, myGroupArea, myTerm, myFunCall);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
