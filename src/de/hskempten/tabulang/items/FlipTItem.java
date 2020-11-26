package de.hskempten.tabulang.items;

public class FlipTItem implements LanguageItem {
    private String myString; //"horizontal"/"vertical"
    private TermItem myTerm;

    public FlipTItem(String myString, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
