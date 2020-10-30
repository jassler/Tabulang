package de.hskempten.tabulang.items;

public class ReturnStmntItem {
    private TermItem myTerm;

    public ReturnStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
