package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.ReturnStmntItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.nodes.Term;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class ReturnStmntType implements Parser {

    public static ReturnStmntType instance = new ReturnStmntType();

    @Override
    public ReturnStmntItem parse(Lexer l) throws ParseTimeException {
        ReturnStmntItem returnItem;

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        TermItem myTerm = TermType.instance.parse(l);
        l.getNextTokenAndExpect(TokenType.SEMICOLON);

        returnItem = new ReturnStmntItem(myTerm);
        return returnItem;
    }
}
