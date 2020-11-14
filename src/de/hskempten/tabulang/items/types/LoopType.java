package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

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

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myIdentifier = IdentifierType.instance.parse(l);
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        myTerm = TermType.instance.parse(l);
        if ("bracket".equals(l.lookahead().getType()) && "{".equals(l.lookahead().getContent())) {
            myLoopBody = LoopBodyType.instance.parse(l);
            item = new LoopItem(myIdentifier, myTerm, myLoopBody);
        } else {
            myLoopStmnt = LoopStmntType.instance.parse(l);
            item = new LoopItem(myIdentifier, myTerm, myLoopStmnt);
        }


        return item;
    }
}
