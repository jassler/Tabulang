package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IntervallItem implements LanguageItem {
    private TermItem myTerm;
    private TermItem mySecondTerm;
    private TextPosition myTextPosition;

    public IntervallItem(TermItem myTerm, TermItem mySecondTerm) {
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
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

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

}
