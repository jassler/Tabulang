package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class TermType implements Parser {

    public static TermType instance = new TermType();

    @Override
    public TermItem parse(Lexer l) throws ParseTimeException {
        TermItem item;

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
        FunCallItem myFunCall;


        //TODO find break conditions for term (loop, markStmnt, groupStmnt)

        switch (l.lookahead().getType()) {
            case "variable" -> {
                if ("bracket".equals(l.lookahead(2).getType()) && "(".equals(l.lookahead(2).getContent())) {
                    myFunCall = FunCallType.instance.parse(l);
                    myTermR = TermRType.instance.parse(l);
                    item = new TermItem(myTermR, myFunCall);
                } else {
                    myIdentifier = IdentifierType.instance.parse(l);
                    myTermR = TermRType.instance.parse(l);
                    item = new TermItem(myTermR, myIdentifier);
                }
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
                    case "distinct" -> {
                        myDistinctT = DistinctTType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermItem(myTermR, myDistinctT);
                    }
                    case "average", "count" -> {
                        myAggregationT = AggregationTType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermItem(myTermR, myAggregationT);
                    }
                    case "horizontalflip", "verticalflip" -> {
                        myFlipT = FlipTType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermItem(myTermR, myFlipT);
                    }
                    case "null" -> {
                        throw new ParseTimeException(l, "Not yet implemented keyword case in the term: " + l.lookahead().getContent());
                    }
                    default -> {
                        throw new ParseTimeException(l, "Not yet implemented keyword case in term: " + l.lookahead().getContent());
                    }
                }
            }
            case "number", "quotedString" -> {
                myOrdinal = OrdinalType.instance.parse(l);
                myTermR = TermRType.instance.parse(l);
                item = new TermItem(myTermR, myOrdinal);
            }
            case "bracket" -> {
                // TODO difference between "'(' term ')' termR" and "ordinal termR" if ordinal is a tuple?
                if ("[".equals(l.lookahead().getContent())) {
                    myOrdinal = OrdinalType.instance.parse(l);
                    myTermR = TermRType.instance.parse(l);
                    item = new TermItem(myTermR, myOrdinal);
                } else if("(".equals(l.lookahead().getContent())){
                    l.getNextTokenAndExpect(TokenType.BRACKET);
                    myTerm = TermType.instance.parse(l);
                    l.getNextTokenAndExpect(TokenType.BRACKET);
                    myTermR = TermRType.instance.parse(l);
                    item = new TermItem(myTerm,myTermR);
                }else{
                    throw new ParseTimeException(l, "Not yet implemented bracket case in term: " + l.lookahead().getContent());
                }
            }
            default -> throw new ParseTimeException(l, "Not yet implemented case in term: " + l.lookahead().getContent());
        }

        return item;
    }
}