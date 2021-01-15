package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.QUANTIFIED_EXISTS;

public class ExistsPredItem extends LanguageItemAbstract {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;
    private PredItem myPred;

    public ExistsPredItem(IdentifierItem myIdentifier, TermItem myTerm, PredItem myPred) {
        super(QUANTIFIED_EXISTS);
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyPred(myPred);
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

    public PredItem getMyPred() {
        return myPred;
    }

    public void setMyPred(PredItem myPred) {
        this.myPred = myPred;
    }
}
