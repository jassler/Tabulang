package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.DistinctTItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class DistinctTType implements Parser {

    public static DistinctTType instance = new DistinctTType();

    @Override
    public DistinctTItem parse(Lexer l) throws ParseTimeException {
        DistinctTItem item;

        ArrayList<IdentifierItem> myIdentifiers = new ArrayList<>();
        TermItem myTerm;

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        while (!("keyword".equals(l.lookahead().getType()) && "from".equals(l.lookahead().getContent()))) {
            myIdentifiers.add(IdentifierType.instance.parse(l));
        }
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);

        item = new DistinctTItem(myIdentifiers, myTerm);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
