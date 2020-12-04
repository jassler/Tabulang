package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.DistinctTItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

import java.util.ArrayList;

public class DistinctTType implements LanguageType {

    public static DistinctTType instance = new DistinctTType();

    @Override
    public DistinctTItem parse(Lexer l) throws ParseTimeException {
        DistinctTItem item;

        ArrayList<IdentifierItem> myIdentifiers = new ArrayList<>();
        TermItem myTerm;

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        while (!("keyword".equals(l.lookahead().getType()) && "from".equals(l.lookahead().getContent()))) {
            myIdentifiers.add(IdentifierType.instance.parse(l));
        }
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);

        item = new DistinctTItem(myIdentifiers, myTerm);

        return item;
    }
}