package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class VListItem implements LanguageItem {
    private IdentifierItem myIdentifier;
    private ArrayList<IdentifierItem> myOtherIdentifiers;

    public VListItem() {
    }

    public VListItem(IdentifierItem myIdentifier) {
        this.setMyIdentifier(myIdentifier);
    }

    public VListItem(IdentifierItem myIdentifier, ArrayList<IdentifierItem> myOtherIdentifiers) {
        this.setMyIdentifier(myIdentifier);
        this.setMyOtherIdentifiers(myOtherIdentifiers);
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public ArrayList<IdentifierItem> getMyOtherIdentifiers() {
        return myOtherIdentifiers;
    }

    public void setMyOtherIdentifiers(ArrayList<IdentifierItem> myOtherIdentifiers) {
        this.myOtherIdentifiers = myOtherIdentifiers;
    }
}
