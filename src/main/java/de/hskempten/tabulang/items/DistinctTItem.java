package de.hskempten.tabulang.items;

import java.util.ArrayList;

import static de.hskempten.tabulang.items.LanguageItemType.DISTINCT_ITEM;

public class DistinctTItem extends LanguageItemAbstract {
    private ArrayList<IdentifierItem> myIdentifiers;
    private TermItem myTerm;

    public DistinctTItem(ArrayList<IdentifierItem> myIdentifiers, TermItem myTerm) {
        super(DISTINCT_ITEM);
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
}
