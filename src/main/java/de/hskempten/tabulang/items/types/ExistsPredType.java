package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.ExistsPredItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class ExistsPredType implements Parser {

    public static ExistsPredType instance = new ExistsPredType();

    @Override
    public ExistsPredItem parse(Lexer l) throws ParseTimeException {
        ExistsPredItem item;

        IdentifierItem myIdentifier;
        TermItem myTerm;
        PredItem myPred;

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myIdentifier = IdentifierType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType()) && "in".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
        } else {
            throw new ParseTimeException(l, "Expected keyword 'in', but got: " + l.lookahead().getContent());
        }
        myTerm = TermType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType()) && "suchThat".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
        } else {
            throw new ParseTimeException(l, "Expected keyword 'suchThat', but got: " + l.lookahead().getContent());
        }
        myPred = PredType.instance.parse(l);

        item = new ExistsPredItem(myIdentifier, myTerm, myPred);

        return item;
    }
}
