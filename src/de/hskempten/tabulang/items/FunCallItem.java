package de.hskempten.tabulang.items;

public class FunCallItem implements LanguageItem {
    /*
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
    */

    private IdentifierItem myIdentifier;
    private TupelItem myTupel;

    public FunCallItem(IdentifierItem myIdentifier, TupelItem myTupel) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTupel(myTupel);
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public TupelItem getMyTupel() {
        return myTupel;
    }

    public void setMyTupel(TupelItem myTupel) {
        this.myTupel = myTupel;
    }
}
