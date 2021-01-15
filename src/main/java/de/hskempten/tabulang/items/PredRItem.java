package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.PREDR_BOOL;
import static de.hskempten.tabulang.items.LanguageItemType.PREDR_NULL;

public class PredRItem extends LanguageItemAbstract {
    private BinBoolItem myBinBool;
    private PredItem myPred;

    public PredRItem(BinBoolItem myBinBool, PredItem myPred) {
        super(PREDR_BOOL);
        this.setMyBinBool(myBinBool);
        this.setMyPred(myPred);
    }

    public PredRItem() {
        super(PREDR_NULL);
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
}
