package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class TermRItem extends LanguageItemAbstract implements TermOrRItem {
    //myPreds darf nicht null sein; pred+
    private PredItem myPred;
    private TermRItem myTermR;
    private TermItem myPreviousTerm;
    private TermItem myTerm;
    private OperatorItem myOperator;
    private MarkStmntItem myMarkStmnt;
    private TupelItem myTupel;
    //"filter", "intersect", "unite", "."
    private String myString;

    public TermRItem(PredItem myPred, TermRItem myTermR, String myString) {
        super(TERMR_FILTER);
        this.myPred = myPred;
        this.myTermR = myTermR;
        this.myString = myString;
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm, String myString) {
        super(switch (myString) {
            case "intersect" -> TERMR_INTERSECT;
            case "unite" -> TERMR_UNITE;
            default -> throw new IllegalStateException("Unexpected value: " + myString);
        });
        this.myTermR = myTermR;
        this.myTerm = myTerm;
        this.myString = myString;

    }

    public TermRItem(PredItem myPred, TermRItem myTermR) {
        super(TERMR_FILTER);
        this.setMyPred(myPred);
        this.myTermR = myTermR;
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm) {
        super(TERMR_DOT);
        this.setMyTermR(myTermR);
        this.setMyTerm(myTerm);
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm, OperatorItem myOperator) {
        super(TERMR_OPERATOR);
        this.setMyTermR(myTermR);
        this.setMyTerm(myTerm);
        this.setMyOperator(myOperator);
    }

    public TermRItem(TermRItem myTermR, MarkStmntItem myMarkStmnt) {
        super(TERMR_MARK);
        this.setMyTermR(myTermR);
        this.setMyMarkStmnt(myMarkStmnt);
    }

    public TermRItem(TermRItem myTermR, TupelItem myTupel) {
        super(TERMR_TUPEL);
        this.setMyTermR(myTermR);
        this.setMyTupel(myTupel);
    }

    public TermRItem() {
        super(TERMR_NULL);
    }

    public TermRItem getMyTermR() {
        return myTermR;
    }

    public void setMyTermR(TermRItem myTermR) {
        this.myTermR = myTermR;
    }

    public TermItem getMyPreviousTerm() {
        return myPreviousTerm;
    }

    public void setMyPreviousTerm(TermItem myPreviousTerm) {
        this.myPreviousTerm = myPreviousTerm;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public OperatorItem getMyOperator() {
        return myOperator;
    }

    public void setMyOperator(OperatorItem myOperator) {
        this.myOperator = myOperator;
    }

    public MarkStmntItem getMyMarkStmnt() {
        return myMarkStmnt;
    }

    public void setMyMarkStmnt(MarkStmntItem myMarkStmnt) {
        this.myMarkStmnt = myMarkStmnt;
    }

    public TupelItem getMyTupel() {
        return myTupel;
    }

    public void setMyTupel(TupelItem myTupel) {
        this.myTupel = myTupel;
    }

    public PredItem getMyPred() {
        return myPred;
    }

    public void setMyPred(PredItem myPred) {
        this.myPred = myPred;
    }
}
