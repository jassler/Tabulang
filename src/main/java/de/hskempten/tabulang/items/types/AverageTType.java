package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.AverageTItem;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;
import de.hskempten.tabulang.types.LanguageType;

public class AverageTType implements LanguageType {

    public static AverageTType instance = new AverageTType();

    @Override
    public AverageTItem parse(Lexer l) throws ParseTimeException {
        AverageTItem item;

        IdentifierItem myIdentifier;
        TermItem myTerm;

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myIdentifier = IdentifierType.instance.parse(l);
        myTerm = TermType.instance.parse(l);

        item = new AverageTItem(myIdentifier, myTerm);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
