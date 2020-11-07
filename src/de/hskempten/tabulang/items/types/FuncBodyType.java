package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.FuncBodyItem;
import de.hskempten.tabulang.items.ReturnStmntItem;
import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;
import java.util.LinkedList;

public class FuncBodyType implements Parser {

    public static FuncBodyType instance = new FuncBodyType();

    @Override
    public FuncBodyItem parse(Lexer l) throws ParseTimeException {
        FuncBodyItem bodyItem;


        LinkedList<StatementItem> myStatements = null;
        ArrayList<ReturnStmntItem> myReturnStmnts = null;
        ReturnStmntItem myReturnStmnt = null;

        switch (l.lookahead().getType()) {
            case "bracket" -> {
                if (!l.lookahead().getContent().equals("{")) {
                    throw new ParseTimeException("Illegal bracket: Expected '{' but got " + l.lookahead().getContent());
                }
                l.getNextTokenAndExpect(TokenType.BRACKET);
                while (!(l.lookahead().getType().equals("bracket") && l.lookahead().getContent().equals("}"))) {
                    //TODO senseless for the case of many statements and one returnStatement
                    if (l.lookahead().getType().equals("keyword") && l.lookahead().getContent().equals("return")) {
                        if (myReturnStmnt == null) {
                            myReturnStmnt = ReturnStmntType.instance.parse(l);
                        } else {
                            if (myReturnStmnts == null) {
                                myReturnStmnts = new ArrayList<>();
                                myReturnStmnts.add(myReturnStmnt);
                            }
                            myReturnStmnts.add(ReturnStmntType.instance.parse(l));
                        }
                    } else {
                        if (myStatements == null) {
                            myStatements = new LinkedList<>();
                        }
                        myStatements.add(StatementType.instance.parse(l));
                    }
                }
            }
            default -> throw new ParseTimeException("Illegal type: Expected '{' but got " + l.lookahead().getContent());
        }

        if (myReturnStmnts != null && !myReturnStmnts.isEmpty()) {
            bodyItem = new FuncBodyItem(myReturnStmnts);
        } else if (myReturnStmnt != null) {
            bodyItem = new FuncBodyItem(myReturnStmnt);
        } else if (myStatements != null && !myStatements.isEmpty()) {
            bodyItem = new FuncBodyItem(myStatements);
        } else {
            throw new ParseTimeException("FuncBody cannot be empty");
        }

        return bodyItem;
    }
}
