package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class PredRItem implements LanguageItem {
    private BinBoolItem myBinBool;
    private PredItem myPred;

    LanguageItemType itemType;
    private TextPosition myTextPositon;

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
    public TextPosition getTextPosition() {
        return myTextPositon;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPositon = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }

    public void setLanguageItemType(LanguageItemType itemType) {
        this.itemType = itemType;
    }
}
