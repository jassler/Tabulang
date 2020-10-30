package de.hskempten.tabulang.items;

public class PredItem {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;
    private PredRItem myPredR;
    private BinRelSymItem myBinResSym;
    private TermItem mySecondTerm;
    private PredItem myPred;
    private PQuantifiedItem myPQuantified;
    private Boolean myBoolean;

    public PredItem(IdentifierItem myIdentifier, TermItem myTerm, PredRItem myPredR) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
    }

    public PredItem(TermItem myTerm, PredRItem myPredR, BinRelSymItem myBinResSym, TermItem mySecondTerm) {
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
        this.setMyBinResSym(myBinResSym);
        this.setMySecondTerm(mySecondTerm);
    }

    public PredItem(TermItem myTerm, PredRItem myPredR) {
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
    }

    public PredItem(PredRItem myPredR, PredItem myPred) {
        this.setMyPredR(myPredR);
        this.setMyPred(myPred);
    }

    public PredItem(PredRItem myPredR, PQuantifiedItem myPQuantified) {
        this.setMyPredR(myPredR);
        this.setMyPQuantified(myPQuantified);
    }

    public PredItem(PredRItem myPredR, Boolean myBoolean) {
        this.myPredR = myPredR;
        this.myBoolean = myBoolean;
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

    public BinRelSymItem getMyBinResSym() {
        return myBinResSym;
    }

    public void setMyBinResSym(BinRelSymItem myBinResSym) {
        this.myBinResSym = myBinResSym;
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
}
