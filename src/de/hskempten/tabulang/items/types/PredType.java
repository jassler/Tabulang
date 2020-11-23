package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class PredType implements LanguageType {

    public static PredType instance = new PredType();

    @Override
    public PredItem parse(Lexer l) throws ParseTimeException {
        PredItem item;

        IdentifierItem myIdentifier;
        TermItem myTerm;
        PredRItem myPredR;
        BinRelSymItem myBinRelSym;
        TermItem mySecondTerm;
        PredItem myPred;
        PQuantifiedItem myPQuantified;
        Boolean myBoolean;
        FunCallItem myFunCallItem;

        switch (l.lookahead().getType()) {
            case "variable" -> {
                if ("keyword".equals(l.lookahead(2).getType()) && "in".equals(l.lookahead(2).getContent())) {
                    myIdentifier = IdentifierType.instance.parse(l);
                    l.getNextTokenAndExpect(TokenType.KEYWORD);
                    myTerm = TermType.instance.parse(l);
                    myPredR = PredRType.instance.parse(l);
                    item = new PredItem(myIdentifier, myTerm, myPredR);
                } else if ("bracket".equals(l.lookahead(2).getType()) && "(".equals(l.lookahead(2).getContent())) {
                    myFunCallItem = FunCallType.instance.parse(l);
                    myPredR = PredRType.instance.parse(l);
                    item = new PredItem(myFunCallItem, myPredR);
                } else {
                    myTerm = TermType.instance.parse(l);
                    if ("binRelSym".equals(l.lookahead().getType())) {
                        myBinRelSym = BinRelSymType.instance.parse(l);
                        mySecondTerm = TermType.instance.parse(l);
                        myPredR = PredRType.instance.parse(l);
                        item = new PredItem(myTerm, myPredR, myBinRelSym, mySecondTerm);
                    } else {
                        myPredR = PredRType.instance.parse(l);
                        item = new PredItem(myTerm, myPredR);
                    }
                }
            }
            case "keyword" -> {
                switch (l.lookahead().getContent()) {
                    case "not" -> {
                        l.getNextTokenAndExpect(TokenType.KEYWORD);
                        myPred = PredType.instance.parse(l);
                        myPredR = PredRType.instance.parse(l);
                        item = new PredItem(myPredR, myPred);
                    }
                    case "exists", "forAll" -> {
                        myPQuantified = PQuantifiedType.instance.parse(l);
                        myPredR = PredRType.instance.parse(l);
                        item = new PredItem(myPredR, myPQuantified);
                    }
                    case "true", "false" -> {
                        myBoolean = Boolean.parseBoolean(l.lookahead().getContent());
                        myPredR = PredRType.instance.parse(l);
                        item = new PredItem(myPredR, myBoolean);
                    }
                    default -> {
                        myTerm = TermType.instance.parse(l);
                        if ("binRelSym".equals(l.lookahead().getType())) {
                            myBinRelSym = BinRelSymType.instance.parse(l);
                            mySecondTerm = TermType.instance.parse(l);
                            myPredR = PredRType.instance.parse(l);
                            item = new PredItem(myTerm, myPredR, myBinRelSym, mySecondTerm);
                        } else {
                            myPredR = PredRType.instance.parse(l);
                            item = new PredItem(myTerm, myPredR);
                        }
                    }
                }
            }
            case "bracket" -> {
                if ("(".equals(l.lookahead().getContent())) {
                    l.getNextTokenAndExpect(TokenType.BRACKET);
                    myPred = PredType.instance.parse(l);
                    if ("bracket".equals(l.lookahead().getType()) && ")".equals(l.lookahead().getContent())) {
                        l.getNextTokenAndExpect(TokenType.BRACKET);
                        myPredR = PredRType.instance.parse(l);
                        item = new PredItem(myPredR, myPred);
                    } else {
                        throw new ParseTimeException(l, "Illegal end of PredItem: " + l.lookahead().getContent());
                    }
                } else {
                    throw new ParseTimeException(l, "Illegal bracket: " + l.lookahead().getContent());
                }
            }
            default -> {
                myTerm = TermType.instance.parse(l);
                if ("binRelSym".equals(l.lookahead().getType())) {
                    myBinRelSym = BinRelSymType.instance.parse(l);
                    mySecondTerm = TermType.instance.parse(l);
                    myPredR = PredRType.instance.parse(l);
                    item = new PredItem(myTerm, myPredR, myBinRelSym, mySecondTerm);
                } else {
                    myPredR = PredRType.instance.parse(l);
                    item = new PredItem(myTerm, myPredR);
                }
            }
        }


        return item;
    }
}
