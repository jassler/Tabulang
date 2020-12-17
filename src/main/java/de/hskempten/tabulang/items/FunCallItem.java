package de.hskempten.tabulang.items;

import java.util.ArrayList;

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

    private TermItem termIdentifier;
    private ArrayList<TermItem> terms;

    public FunCallItem(IdentifierItem myIdentifier, ArrayList<TermItem> myTerms) {
        this.setMyIdentifier(myIdentifier);
        this.setTerms(myTerms);
    }

    public FunCallItem(IdentifierItem myIdentifier, TupelItem myTupel) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTupel(myTupel);
    }

    public TermItem getTermIdentifier() {
        return termIdentifier;
    }

    public void setTermIdentifier(TermItem termIdentifier) {
        this.termIdentifier = termIdentifier;
    }

    public ArrayList<TermItem> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<TermItem> terms) {
        this.terms = terms;
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

    @Override
    public LanguageItemType getLanguageItemType() {
        return LanguageItemType.TERM_FUNCALL;
    }
}
