package de.hskempten.tabulang.items;

import java.util.ArrayList;

import static de.hskempten.tabulang.items.LanguageItemType.TERM_FUNCALL;

public class FunCallItem extends LanguageItemAbstract {

    private IdentifierItem myIdentifier;
    private TupelItem myTupel;

    private TermItem termIdentifier;
    private ArrayList<TermItem> terms;

    public FunCallItem(IdentifierItem myIdentifier, ArrayList<TermItem> myTerms) {
        super(TERM_FUNCALL);
        this.setMyIdentifier(myIdentifier);
        this.setTerms(myTerms);
    }

    public FunCallItem(IdentifierItem myIdentifier, TupelItem myTupel) {
        super(TERM_FUNCALL);
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
}
