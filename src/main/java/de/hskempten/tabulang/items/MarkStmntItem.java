package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkStmntItem implements LanguageItem {
    private TermItem myTerm;
    private TermItem mySecondTerm;
    private PredItem myPred;

    private LanguageItemType itemType;
    private TextPosition myTextPosition;


    public MarkStmntItem(TermItem myTerm, TermItem mySecondTerm, PredItem myPred) {
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
        this.setMyPred(myPred);
        this.itemType = LanguageItemType.MARK_WITHIF;
    }

    public MarkStmntItem(TermItem myTerm, TermItem mySecondTerm) {
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
        this.itemType = LanguageItemType.MARK_WITHOUTIF;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
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

    public void setLanguageItemType(LanguageItemType itemType) {
        this.itemType = itemType;
    }
}
