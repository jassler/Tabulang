package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class ProceduralFType implements Parser {

    public static ProceduralFType instance = new ProceduralFType();

    @Override
    public ProceduralFItem parse(Lexer l) throws ParseTimeException {

        ProceduralFItem proceduralF;

        l.getNextToken();
        IdentifierItem myIdentifier = IdentifierType.instance.parse(l);
        Token bracket = l.getNextTokenAndExpect(TokenType.BRACKET);
        if (!bracket.getContent().equals("("))
            throw new ParseTimeException("Illegal bracket: Expected '(' but got " + l.lookahead().getContent());
        VListItem myVList = VListType.instance.parse(l);
        if (!l.lookahead().getContent().equals(")"))
            throw new ParseTimeException("Illegal bracket: Expected ')' but got " + l.lookahead().getContent());

        l.getNextToken();
        switch (l.lookahead().getType()) {
            case "bracket" -> {
                switch (l.lookahead().getContent()) {
                    case "{" -> {
                        FuncBodyItem myFuncBody = FuncBodyType.instance.parse(l);
                        proceduralF = new ProceduralFItem(myIdentifier, myVList, myFuncBody);
                        l.getNextTokenAndExpect(TokenType.BRACKET);
                    }
                    default -> throw new ParseTimeException("Illegal bracket: Expected '{' but got " + l.lookahead().getContent());
                }
            }
            default -> {
                TermItem myTerm = TermType.instance.parse(l);
                proceduralF = new ProceduralFItem(myIdentifier, myVList, myTerm);
                l.getNextTokenAndExpect(TokenType.SEMICOLON);
            }
        }

        return proceduralF;
    }
}
