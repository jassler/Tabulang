package de.hskempten.tabulang.items;

public class FunCallItem {
    private TermItem myTerm;
    private TupelItem myTupel;

    public FunCallItem(TermItem myTerm, TupelItem myTupel) {
        this.setMyTerm(myTerm);
        this.setMyTupel(myTupel);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public TupelItem getMyTupel() {
        return myTupel;
    }

    public void setMyTupel(TupelItem myTupel) {
        this.myTupel = myTupel;
    }
}
