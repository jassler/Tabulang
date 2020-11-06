package de.hskempten.tabulang.items;

public class PredRItem {
    private BinBoolItem myBinBool;
    private PredItem myPred;

    public PredRItem(BinBoolItem myBinBool, PredItem myPred) {
        this.setMyBinBool(myBinBool);
        this.setMyPred(myPred);
    }

    public PredRItem() {
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
