package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class VListItem implements LanguageItem {
    private IdentifierItem myIdentifier;
    private ArrayList<IdentifierItem> myOtherIdentifiers;

    private LanguageItemType itemType;

    public VListItem() {
        this.itemType = LanguageItemType.VLIST_EMPTY;
    }

    public VListItem(IdentifierItem myIdentifier) {
        this.setMyIdentifier(myIdentifier);
        this.itemType= LanguageItemType.VLIST_ONE;
    }

    public VListItem(IdentifierItem myIdentifier, ArrayList<IdentifierItem> myOtherIdentifiers) {
        this.setMyIdentifier(myIdentifier);
        this.setMyOtherIdentifiers(myOtherIdentifiers);
        this.itemType = LanguageItemType.VLIST_MULTI;
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

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}