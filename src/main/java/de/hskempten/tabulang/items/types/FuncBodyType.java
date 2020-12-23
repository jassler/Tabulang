package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.FuncBodyItem;
import de.hskempten.tabulang.items.StatementAnyItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.LinkedList;

public class FuncBodyType implements Parser {

    public static FuncBodyType instance = new FuncBodyType();

    @Override
    public FuncBodyItem parse(Lexer l) throws ParseTimeException {
        FuncBodyItem bodyItem;

        LinkedList<StatementAnyItem> myStatements = new LinkedList<StatementAnyItem>();

        switch (l.lookahead().getType()) {
            case "bracket" -> {
                if (!l.lookahead().getContent().equals("{")) {
                    throw new ParseTimeException(l, "Illegal bracket: Expected '{' but got " + l.lookahead().getContent());
                }
                l.getNextTokenAndExpect(TokenType.BRACKET);
                while (!(l.lookahead().getType().equals("bracket") && l.lookahead().getContent().equals("}"))) {
                    //TODO senseless for the case of many statements and one returnStatement
                    if (l.lookahead().getType().equals("keyword") && l.lookahead().getContent().equals("return")) {
                        myStatements.add(ReturnStmntType.instance.parse(l));
                    } else {
                        myStatements.add(StatementType.instance.parse(l));
                    }
                }
                l.getNextTokenAndExpect(TokenType.BRACKET);
            }
            case "keyword" -> {
                myStatements.add(ReturnStmntType.instance.parse(l));
            }
            default -> throw new ParseTimeException(l, "Illegal type: Expected '{' but got " + l.lookahead().getContent());
        }

        bodyItem = new FuncBodyItem(myStatements);

        return bodyItem;
    }
}
