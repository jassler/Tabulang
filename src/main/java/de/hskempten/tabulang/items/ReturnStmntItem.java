package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import static de.hskempten.tabulang.items.LanguageItemType.ANYSTATEMENT_RETURN;

public class ReturnStmntItem extends LanguageItemAbstract implements StatementAnyItem {
    private TermItem myTerm;

    public ReturnStmntItem(TermItem myTerm) {
        super(ANYSTATEMENT_RETURN);
        this.setMyTerm(myTerm);
    }

    public ReturnStmntItem(TermItem myTerm, TextPosition textPosition) {
        super(ANYSTATEMENT_RETURN, textPosition);
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
