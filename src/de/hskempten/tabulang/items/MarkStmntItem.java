package de.hskempten.tabulang.items;

public class MarkStmntItem {
    private TermItem myTerm;
    private TermItem mySecondTerm;
    private PredItem myPred;

    public MarkStmntItem(TermItem myTerm, TermItem mySecondTerm, PredItem myPred) {
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
        this.setMyPred(myPred);
    }

    public MarkStmntItem(TermItem myTerm, TermItem mySecondTerm) {
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
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
}