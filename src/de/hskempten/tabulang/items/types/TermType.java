package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class TermType implements Parser {

    public static TermType instance = new TermType();

    @Override
    public TermItem parse(Lexer l) throws ParseTimeException {
        TermItem item = null;

        //'('
        TermItem myTerm;
        //')'
        TermRItem myTermR;
        IdentifierItem myIdentifier;
        LoopItem myLoop;
        FlipTItem myFlipT;
        OrdinalItem myOrdinal;
        DirectionalTermItem myDirectionalTerm;
        FunDefItem myFunDef;
        AggregationTItem myAggregationT;
        DistinctTItem myDistinctT;


        //TODO find break conditions for term (loop, markStmnt, groupStmnt)

        switch (l.lookahead().getType()) {
            case "variable" -> {
                myIdentifier = IdentifierType.instance.parse(l);
                myTermR = TermRType.instance.parse(l);
                item = new TermItem(myTermR, myIdentifier);
            }
            case "keyword" -> {
                switch (l.lookahead().getContent()) {
                    case "horizontal", "vertical" -> {
                        myDirectionalTerm = DirectionalTermType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermItem(myTermR, myDirectionalTerm);
                    }
                    case "for" -> {
                        myLoop = LoopType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermItem(myTermR, myLoop);
                    }
                    case "verticalflip", "horizontalflip", "count", "average", "distinct", "null" -> {
                        throw new ParseTimeException(l, "Not yet implemented keyword case in term: " + l.lookahead().getContent());
                    }
                }
            }
            case "number" -> {
                myOrdinal = OrdinalType.instance.parse(l);
                myTermR = TermRType.instance.parse(l);
                item = new TermItem(myTermR, myOrdinal);
            }
            case "bracket" -> throw new ParseTimeException(l, "Not yet implemented bracket case in term: " + l.lookahead().getContent());
            default -> throw new ParseTimeException(l, "Not yet implemented case in term: " + l.lookahead().getContent());
        }


        if (item == null) {
            throw new ParseTimeException("TermItem == null");
        }

        return item;
    }
}
