package de.hskempten.tabulang.items;

public class AverageTItem extends LanguageItemAbstract {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;

    public AverageTItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
