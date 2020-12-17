package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class DistinctTItem implements LanguageItem {
    private ArrayList<IdentifierItem> myIdentifiers;
    private TermItem myTerm;
    private LanguageItemType itemType = LanguageItemType.DISTINCT_ITEM;

    public DistinctTItem(ArrayList<IdentifierItem> myIdentifiers, TermItem myTerm) {
        this.setMyIdentifiers(myIdentifiers);
        this.setMyTerm(myTerm);
    }

    public ArrayList<IdentifierItem> getMyIdentifiers() {
        return myIdentifiers;
    }

    public void setMyIdentifiers(ArrayList<IdentifierItem> myIdentifiers) {
        this.myIdentifiers = myIdentifiers;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return LanguageItemType.DISTINCT_ITEM;
    }
}
