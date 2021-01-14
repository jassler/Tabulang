package de.hskempten.tabulang.items;

import java.util.ArrayList;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class VListItem extends LanguageItemAbstract implements LanguageItem {
    private IdentifierItem myIdentifier;
    private ArrayList<IdentifierItem> myOtherIdentifiers;

    public VListItem() {
        super(VLIST_EMPTY);
    }

    public VListItem(IdentifierItem myIdentifier) {
        super(VLIST_ONE);
        this.setMyIdentifier(myIdentifier);
    }

    public VListItem(IdentifierItem myIdentifier, ArrayList<IdentifierItem> myOtherIdentifiers) {
        super(VLIST_MULTI);
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
