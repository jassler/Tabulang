package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class FunCallItem implements LanguageItem {

    private IdentifierItem myIdentifier;
    private TupelItem myTupel;

    private TermItem termIdentifier;
    private ArrayList<TermItem> terms;
    private TextPosition myTextPosition;

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
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return LanguageItemType.TERM_FUNCALL;
    }
}
