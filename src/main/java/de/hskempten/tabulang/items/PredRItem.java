package de.hskempten.tabulang.items;

public class PredRItem implements LanguageItem {
    private BinBoolItem myBinBool;
    private PredItem myPred;

    LanguageItemType itemType;

    public PredRItem(BinBoolItem myBinBool, PredItem myPred) {
        this.setMyBinBool(myBinBool);
        this.setMyPred(myPred);
        this.itemType = LanguageItemType.PREDR_BOOL;
    }

    public PredRItem() {
        this.itemType = LanguageItemType.PREDR_NULL;
    }

    public BinBoolItem getMyBinBool() {
        return myBinBool;
    }

    public void setMyBinBool(BinBoolItem myBinBool) {
        this.myBinBool = myBinBool;
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
