package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.FunCallItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;
import de.hskempten.tabulang.tokenizer.Token;

import java.util.ArrayList;

public class FunCallType implements Parser {

    public static FunCallType instance = new FunCallType();

    @Override
    public FunCallItem parse(Lexer l) throws ParseTimeException {
        FunCallItem item;

        IdentifierItem myIdentifier;

        ArrayList<TermItem> myTerms = new ArrayList<TermItem>();

        TextPosition startP = l.lookahead().getPosition();
        myIdentifier = IdentifierType.instance.parse(l);
        l.getNextTokenAndExpect(TokenType.BRACKET);


        while (!l.lookahead().getContent().equals(")")) {
            if (myTerms.isEmpty()) {
                myTerms.add(TermType.instance.parse(l));
            }
            Token bracketOrComma = l.lookahead();
            switch (bracketOrComma.getType()) {
                case "bracket":
                    if (!bracketOrComma.getContent().equals(")"))
                        throw new ParseTimeException(l, "Illegal bracket: Expected ')' but got " + l.lookahead().getContent());
                    break;
                case "comma":
                    l.getNextTokenAndExpect(TokenType.COMMA);
                    myTerms.add(TermType.instance.parse(l));
                    break;
                default:
                    throw new ParseTimeException(l, "Illegal Token: " + l.lookahead().getContent());
            }
        }
        l.getNextTokenAndExpect(TokenType.BRACKET);
        item = new FunCallItem(myIdentifier, myTerms);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
