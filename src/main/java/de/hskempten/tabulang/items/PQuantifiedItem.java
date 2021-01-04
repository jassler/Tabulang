package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class PQuantifiedItem implements LanguageItem {
    private ExistsPredItem myExistsPred;
    private ForallPredItem myForallPred;

    LanguageItemType itemType;
    private TextPosition myTextPosition;

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
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
