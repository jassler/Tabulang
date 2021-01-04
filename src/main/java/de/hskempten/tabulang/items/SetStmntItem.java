package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class SetStmntItem implements LanguageItem {
    private TermItem myTerm;
    private LanguageItemType itemType = LanguageItemType.ANYSTATEMENT_SET;
    private TextPosition myTextPositon;

    public SetStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
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
}
