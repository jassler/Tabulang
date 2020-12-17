package de.hskempten.tabulang.items;

public class PredItem implements LanguageItem {
    private IdentifierItem myIdentifier;
    private TermItem myTerm;
    private PredRItem myPredR;
    private BinRelSymItem myBinResSym;
    private TermItem mySecondTerm;
    private PredItem myPred;
    private PQuantifiedItem myPQuantified;
    private Boolean myBoolean;
    private FunCallItem myFunCallItem; //TODO add funCall to pred?

    LanguageItemType itemType;

    public PredItem(IdentifierItem myIdentifier, TermItem myTerm, PredRItem myPredR) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
        this.itemType = LanguageItemType.PRED_IN;
    }

    public PredItem(TermItem myTerm, PredRItem myPredR, BinRelSymItem myBinResSym, TermItem mySecondTerm) {
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
        this.setMyBinResSym(myBinResSym);
        this.setMySecondTerm(mySecondTerm);
        this.itemType = LanguageItemType.PRED_BINRELSYM;
    }

    public PredItem(TermItem myTerm, PredRItem myPredR) {
        this.setMyTerm(myTerm);
        this.setMyPredR(myPredR);
        this.itemType = LanguageItemType.PRED_TERM;
    }

    public PredItem(PredRItem myPredR, PredItem myPred) {
        this.setMyPredR(myPredR);
        this.setMyPred(myPred);
        this.itemType = LanguageItemType.PRED_BRACKET;
        // TODO case for 'not'
    }

    public PredItem(PredRItem myPredR, PQuantifiedItem myPQuantified) {
        this.setMyPredR(myPredR);
        this.setMyPQuantified(myPQuantified);
        this.itemType = LanguageItemType.PRED_QUANTIFIED;
    }

    public PredItem(PredRItem myPredR, Boolean myBoolean) {
        this.myPredR = myPredR;
        this.myBoolean = myBoolean;
        this.itemType = LanguageItemType.PRED_BOOLEAN;
    }

    public PredItem(FunCallItem myFunCallItem, PredRItem myPredR) {
        this.myFunCallItem = myFunCallItem;
        this.myPredR = myPredR;
        this.itemType = LanguageItemType.PRED_FUNCALL;
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

    public PredRItem getMyPredR() {
        return myPredR;
    }

    public void setMyPredR(PredRItem myPredR) {
        this.myPredR = myPredR;
    }

    public BinRelSymItem getMyBinResSym() {
        return myBinResSym;
    }

    public void setMyBinResSym(BinRelSymItem myBinResSym) {
        this.myBinResSym = myBinResSym;
    }

    public TermItem getMySecondTerm() {
        return mySecondTerm;
    }

    public void setMySecondTerm(TermItem mySecondTerm) {
        this.mySecondTerm = mySecondTerm;
    }

    public PredItem getMyPred() {
        return myPred;
    }

    public void setMyPred(PredItem myPred) {
        this.myPred = myPred;
    }

    public PQuantifiedItem getMyPQuantified() {
        return myPQuantified;
    }

    public void setMyPQuantified(PQuantifiedItem myPQuantified) {
        this.myPQuantified = myPQuantified;
    }

    public Boolean getMyBoolean() {
        return myBoolean;
    }

    public void setMyBoolean(Boolean myBoolean) {
        this.myBoolean = myBoolean;
    }

    public FunCallItem getMyFunCallItem() {
        return myFunCallItem;
    }

    public void setMyFunCallItem(FunCallItem myFunCallItem) {
        this.myFunCallItem = myFunCallItem;
    }

    public void setLanguageItemType(LanguageItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
