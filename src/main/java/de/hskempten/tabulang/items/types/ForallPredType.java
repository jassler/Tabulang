package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.ForallPredItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class ForallPredType implements LanguageType {

    public static ForallPredType instance = new ForallPredType();

    @Override
    public ForallPredItem parse(Lexer l) throws ParseTimeException {
        ForallPredItem item;

        IdentifierItem myIdentifier;
        TermItem myTerm;
        PredItem myPred;

        if ("keyword".equals(l.lookahead().getType()) && "forAll".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
        } else {
            throw new ParseTimeException(l, "Expected keyword 'forAll', but got: " + l.lookahead().getContent());
        }
        myIdentifier = IdentifierType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType()) && "in".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
        } else {
            throw new ParseTimeException(l, "Expected keyword 'in', but got: " + l.lookahead().getContent());
        }
        myTerm = TermType.instance.parse(l);
        if ("keyword".equals(l.lookahead().getType()) && "holds".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.KEYWORD);
        } else {
            throw new ParseTimeException(l, "Expected keyword 'holds', but got: " + l.lookahead().getContent());
        }
        myPred = PredType.instance.parse(l);

        item = new ForallPredItem(myIdentifier, myTerm, myPred);

        return item;
    }
}
