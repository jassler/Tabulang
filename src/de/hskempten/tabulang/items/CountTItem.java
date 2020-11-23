package de.hskempten.tabulang.items;

public class CountTItem implements LanguageItem {
    //"horizontal"/"vertical"
    private String myString;
    private TermItem myTerm;

    public CountTItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public CountTItem(String myString, TermItem myTerm) {
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
