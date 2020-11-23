package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class FunDefType implements Parser {

    public static FunDefType instance = new FunDefType();

    @Override
    public FunDefItem parse(Lexer l) throws ParseTimeException {
        FunDefItem item;

        VListItem myVList = null;
        IdentifierItem myIdentifier = null;
        FuncBodyItem myFuncBody = null;
        TermItem myTerm = null;

        if ("bracket".equals(l.lookahead().getType()) && "(".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.BRACKET);
            myVList = VListType.instance.parse(l);
            l.getNextTokenAndExpect(TokenType.BRACKET);
        } else if ("variable".equals(l.lookahead().getType())) {
            myIdentifier = IdentifierType.instance.parse(l);
        } else {
            throw new ParseTimeException(l, "Expected '(' or identifier, but got: " + l.lookahead().getContent());
        }
        //l.getNextTokenAndExpect(TokenType.ARROW); //TODO create new TokenType or extend one
        if ("bracket".equals(l.lookahead().getType()) && "{".equals(l.lookahead().getContent())) {
            myFuncBody = FuncBodyType.instance.parse(l);
        } else {
            myTerm = TermType.instance.parse(l);
            l.getNextTokenAndExpect(TokenType.SEMICOLON);
        }

        if (myIdentifier != null) {
            if (myFuncBody != null) {
                item = new FunDefItem(myIdentifier, myFuncBody);
            } else {
                item = new FunDefItem(myIdentifier, myTerm);
            }
        } else {
            if (myFuncBody != null) {
                item = new FunDefItem(myVList, myFuncBody);
            } else {
                item = new FunDefItem(myVList, myTerm);
            }
        }

        return item;
    }
}
