package de.hskempten.tabulang.items;

public class SetStmntItem {
    private TermItem myTerm;

    public SetStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}