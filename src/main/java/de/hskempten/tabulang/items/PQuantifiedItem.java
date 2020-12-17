package de.hskempten.tabulang.items;

public class PQuantifiedItem implements LanguageItem {
    private ExistsPredItem myExistsPred;
    private ForallPredItem myForallPred;

    LanguageItemType itemType;

    public PQuantifiedItem(ExistsPredItem myExistsPred) {
        this.setMyExistsPred(myExistsPred);
        this.itemType = LanguageItemType.QUANTIFIED_EXISTS;
    }

    public PQuantifiedItem(ForallPredItem myForallPred) {
        this.setMyForallPred(myForallPred);
        this.itemType = LanguageItemType.QUANTIFIED_FORALL;
    }

    public ExistsPredItem getMyExistsPred() {
        return myExistsPred;
    }

    public void setMyExistsPred(ExistsPredItem myExistsPred) {
        this.myExistsPred = myExistsPred;
    }

    public ForallPredItem getMyForallPred() {
        return myForallPred;
    }

    public void setMyForallPred(ForallPredItem myForallPred) {
        this.myForallPred = myForallPred;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
