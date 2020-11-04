package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.util.ArrayList;
import java.util.List;

public class VList extends Node {
    List<Token> list = new ArrayList<Token>();

    public VList(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        while (!l.lookahead().getContent().equals(")")) {
            Token identifier = l.getNextTokenAndExpect(TokenType.VARIABLE);
            String z = identifier.getContent();
            list.add(identifier);
            Token bracketOrComma = l.lookahead();
            String x = bracketOrComma.getType();
            String y = bracketOrComma.getContent();
            switch (bracketOrComma.getType()) {
                case "bracket":
                    if (!bracketOrComma.getContent().equals(")"))
                        throw new ParseTimeException("Illegal bracket: Expected ')' but got " + l.lookahead().getContent());
                    return;
                case "comma":
                    l.getNextTokenAndExpect(TokenType.COMMA);
                    break;
                default:
                    throw new ParseTimeException("Illegal Token: " + l.lookahead().getContent());
            }
        }
    }

    public List<Token> getVList() {
        return list;
    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
