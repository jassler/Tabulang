package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class TermType implements Parser {

    public static TermType instance = new TermType();

    @Override
    public TermItem parse(Lexer l) throws ParseTimeException {
        TermItem item = null;

        //'('
        TermItem myTerm = null;
        //')'
        TermRItem myTermR = null;
        IdentifierItem myIdentifier = null;
        LoopItem myLoop = null;
        FlipTItem myFlipT = null;
        OrdinalItem myOrdinal = null;
        DirectionalTermItem myDirectionalTerm = null;
        FunDefItem myFunDef = null;
        AggregationTItem myAggregationT = null;
        DistinctTItem myDistinctT = null;


        //TODO find break conditions for term (loop, markStmnt, groupStmnt)
        while (!(l.lookahead().getType().equals(";") || l.lookahead().getType().equals(TokenType.BRACKET))) {
            switch (l.lookahead().getType()) {
                case "variable" -> {
                    myIdentifier = IdentifierType.instance.parse(l);
                }
                case "keyword" -> {
                    switch (l.lookahead().getContent()) {
                        case "for", "verticalflip", "horizontalflip", "horizontal", "vertical", "count", "average", "distinct", "null" -> {
                            throw new ParseTimeException(l, "Not yet implemented keyword case in term: " + l.lookahead().getContent());
                        }
                    }
                }
                case "number" -> {
                    myOrdinal = OrdinalType.instance.parse(l);
                }
                case "bracket" -> throw new ParseTimeException(l, "Not yet implemented bracket case in term: " + l.lookahead().getContent());
                default -> throw new ParseTimeException(l, "Not yet implemented case in term: " + l.lookahead().getContent());
            }
            myTermR = TermRType.instance.parse(l);
        }


        if (myTerm != null) {

        } else if (myIdentifier != null) {
            item = new TermItem(myTermR, myIdentifier);
        } else if (myLoop != null) {

        } else if (myFlipT != null) {

        } else if (myOrdinal != null) {
            item = new TermItem(myTermR, myOrdinal);
        } else if (myDirectionalTerm != null) {

        } else if (myFunDef != null) {

        } else if (myDistinctT != null) {

        }


        if (item == null) {
            throw new ParseTimeException("TermItem == null");
        }

        return item;
    }
}
