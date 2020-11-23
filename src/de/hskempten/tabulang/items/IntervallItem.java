package de.hskempten.tabulang.items;

public class IntervallItem implements LanguageItem {
    private TermItem myTerm;
    private TermItem mySecondTerm;

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
}
