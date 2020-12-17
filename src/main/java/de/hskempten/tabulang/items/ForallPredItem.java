package de.hskempten.tabulang.items;

public class ForallPredItem implements LanguageItem {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;
    private PredItem myPred;
    private LanguageItemType itemType = LanguageItemType.QUANTIFIED_FORALL;

    public ForallPredItem(IdentifierItem myIdentifier, TermItem myTerm, PredItem myPred) {
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
    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
