package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class LoopType implements Parser {

    public static LoopType instance = new LoopType();

    public LoopItem parse(Lexer l) throws ParseTimeException {
        LoopItem item;

        //'for'
        IdentifierItem myIdentifier;
        //'in'
        TermItem myTerm;
        LoopStmntItem myLoopStmnt;
        //'('
        LoopBodyItem myLoopBody;
        //')'

        TextPosition startP = l.lookahead().getPosition();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myIdentifier = IdentifierType.instance.parse(l);
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);
        if ("bracket".equals(l.lookahead().getType()) && "{".equals(l.lookahead().getContent())) {
            l.getNextTokenAndExpect(TokenType.BRACKET);
            myLoopBody = LoopBodyType.instance.parse(l);
            l.getNextTokenAndExpect(TokenType.BRACKET);
            item = new LoopItem(myIdentifier, myTerm, myLoopBody);
        } else {
            myLoopStmnt = LoopStmntType.instance.parse(l);
            item = new LoopItem(myIdentifier, myTerm, myLoopStmnt);
        }


        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
