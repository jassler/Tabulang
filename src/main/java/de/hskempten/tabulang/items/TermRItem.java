package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class TermRItem implements LanguageItem {
    //myPreds darf nicht null sein; pred+
    private ArrayList<PredItem> myPreds;
    private TermRItem myTermR;
    private TermItem myTerm;
    private OperatorItem myOperator;
    private MarkStmntItem myMarkStmnt;
    private TupelItem myTupel;
    //"filter", "intersect", "unite", "."
    private String myString;

    private LanguageItemType itemType;

    public TermRItem(ArrayList<PredItem> myPreds, TermRItem myTermR, String myString) {
        this.myPreds = myPreds;
        this.myTermR = myTermR;
        this.myString = myString;
        this.itemType = LanguageItemType.TERMR_FILTER;
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm, String myString) {
        this.myTermR = myTermR;
        this.myTerm = myTerm;
        this.myString = myString;
        switch (myString) {
            case "intersect" -> this.itemType = LanguageItemType.TERMR_INTERSECT;
            case "unite" -> this.itemType = LanguageItemType.TERMR_UNITE;
        }
    }

    public TermRItem(ArrayList<PredItem> myPreds, TermRItem myTermR) {
        this.setMyPreds(myPreds);
        this.myTermR = myTermR;
        this.itemType = LanguageItemType.TERMR_FILTER;
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm) {
        this.setMyTermR(myTermR);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.TERMR_DOT;
    }

    public TermRItem(TermRItem myTermR, TermItem myTerm, OperatorItem myOperator) {
        this.setMyTermR(myTermR);
        this.setMyTerm(myTerm);
        this.setMyOperator(myOperator);
        this.itemType = LanguageItemType.TERMR_OPERATOR;
    }

    public TermRItem(TermRItem myTermR, MarkStmntItem myMarkStmnt) {
        this.setMyTermR(myTermR);
        this.setMyMarkStmnt(myMarkStmnt);
        this.itemType = LanguageItemType.TERMR_MARK;
    }

    public TermRItem(TermRItem myTermR, TupelItem myTupel) {
        this.setMyTermR(myTermR);
        this.setMyTupel(myTupel);
        this.itemType = LanguageItemType.TERMR_TUPEL;
    }

    public TermRItem() {
        this.itemType = LanguageItemType.TERMR_NULL;
    }


    public TermRItem getMyTermR() {
        return myTermR;
    }

    public void setMyTermR(TermRItem myTermR) {
        this.myTermR = myTermR;
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

    public ArrayList<PredItem> getMyPreds() {
        return myPreds;
    }

    public void setMyPreds(ArrayList<PredItem> myPreds) {
        this.myPreds = myPreds;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }

    public void setLanguageItemType(LanguageItemType itemType) {
        this.itemType = itemType;
    }
}
