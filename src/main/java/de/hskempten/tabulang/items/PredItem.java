package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class PredItem extends LanguageItemAbstract implements LanguageItem {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;
    private PredRItem myPredR;
    private BinRelSymItem myBinRelSym;
    private TermItem mySecondTerm;
    private PredItem myPred;
    private PQuantifiedItem myPQuantified;
    private Boolean myBoolean;
    private FunCallItem myFunCallItem; //TODO add funCall to pred?


    public PredItem(IdentifierItem myIdentifier, TermItem myTerm, PredRItem myPredR) {
        super(PRED_IN);
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
    }

    public PredItem(TermItem myTerm, PredRItem myPredR, BinRelSymItem myBinRelSym, TermItem mySecondTerm) {
        super(PRED_BINRELSYM);
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
        this.setMyBinRelSym(myBinRelSym);
        this.setMySecondTerm(mySecondTerm);
    }

    public PredItem(TermItem myTerm, PredRItem myPredR) {
        super(PRED_TERM);
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
    }

    public PredItem(PredRItem myPredR, PredItem myPred) {
        super(PRED_BRACKET);
        // TODO case for 'not'
        this.setMyPredR(myPredR);
        this.setMyPred(myPred);
    }

    public PredItem(PredRItem myPredR, PQuantifiedItem myPQuantified) {
        super(PRED_QUANTIFIED);
        this.setMyPredR(myPredR);
        this.setMyPQuantified(myPQuantified);
    }

    public PredItem(PredRItem myPredR, Boolean myBoolean) {
        super(PRED_BOOLEAN);
        this.myPredR = myPredR;
        this.myBoolean = myBoolean;
    }

    public PredItem(FunCallItem myFunCallItem, PredRItem myPredR) {
        super(PRED_FUNCALL);
        this.myFunCallItem = myFunCallItem;
        this.myPredR = myPredR;
    }

    public PredItem(String s, TermItem myTerm, BinRelSymItem myBinRelSym, TermItem mySecondTerm, PredRItem myPredR) {
        super(PRED_INDEX);
        this.myTerm = myTerm;
        this.myBinRelSym = myBinRelSym;
        this.mySecondTerm = mySecondTerm;
        this.myPredR = myPredR;
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public PredRItem getMyPredR() {
        return myPredR;
    }

    public void setMyPredR(PredRItem myPredR) {
        this.myPredR = myPredR;
    }

    public BinRelSymItem getMyBinRelSym() {
        return myBinRelSym;
    }

    public void setMyBinRelSym(BinRelSymItem myBinRelSym) {
        this.myBinRelSym = myBinRelSym;
    }

    public TermItem getMySecondTerm() {
        return mySecondTerm;
    }

    public void setMySecondTerm(TermItem mySecondTerm) {
        this.mySecondTerm = mySecondTerm;
    }

    public PredItem getMyPred() {
        return myPred;
    }

    public void setMyPred(PredItem myPred) {
        this.myPred = myPred;
    }

    public PQuantifiedItem getMyPQuantified() {
        return myPQuantified;
    }

    public void setMyPQuantified(PQuantifiedItem myPQuantified) {
        this.myPQuantified = myPQuantified;
    }

    public Boolean getMyBoolean() {
        return myBoolean;
    }

    public void setMyBoolean(Boolean myBoolean) {
        this.myBoolean = myBoolean;
    }

    public FunCallItem getMyFunCallItem() {
        return myFunCallItem;
    }

    public void setMyFunCallItem(FunCallItem myFunCallItem) {
        this.myFunCallItem = myFunCallItem;
    }
}
