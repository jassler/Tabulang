package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.VListItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.util.ArrayList;

public class VListType implements Parser {

    public static VListType instance = new VListType();

    @Override
    public VListItem parse(Lexer l) throws ParseTimeException {
        VListItem item;

        IdentifierItem myIdentifier = null;
        ArrayList<IdentifierItem> myOtherIdentifiers = new ArrayList<>();

        //TODO implement case if no identifiers in VList

        while (!l.lookahead().getContent().equals(")")) {
            if (myIdentifier == null) {
                myIdentifier = IdentifierType.instance.parse(l);
            }
            Token bracketOrComma = l.lookahead();
            String x = bracketOrComma.getType();
            String y = bracketOrComma.getContent();
            switch (bracketOrComma.getType()) {
                case "bracket":
                    if (!bracketOrComma.getContent().equals(")"))
                        throw new ParseTimeException("Illegal bracket: Expected ')' but got " + l.lookahead().getContent());
                    break;
                case "comma":
                    l.getNextTokenAndExpect(TokenType.COMMA);
                    myOtherIdentifiers.add(IdentifierType.instance.parse(l));
                    break;
                default:
                    throw new ParseTimeException("Illegal Token: " + l.lookahead().getContent());
            }
        }
        if (myIdentifier == null) {
            item = new VListItem();
        } else if (myOtherIdentifiers.isEmpty()) {
            item = new VListItem(myIdentifier);
        } else {
            item = new VListItem(myIdentifier, myOtherIdentifiers);
        }


        return item;
    }
}
