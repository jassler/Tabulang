package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class ReturnStmntItem implements StatementAnyItem {
    private TermItem myTerm;
    private LanguageItemType itemType = LanguageItemType.ANYSTATEMENT_RETURN;
    private TextPosition myTextPositon;

    public ReturnStmntItem(TermItem myTerm) {
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
