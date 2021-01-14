package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.COUNT_DIRECTIONAL;
import static de.hskempten.tabulang.items.LanguageItemType.COUNT_EMPTY;

public class CountTItem extends LanguageItemAbstract implements LanguageItem {
    //"horizontal"/"vertical"
    private String myString;
    private TermItem myTerm;

    public CountTItem(TermItem myTerm) {
        super(COUNT_EMPTY);
        this.setMyTerm(myTerm);
    }

    public CountTItem(String myString, TermItem myTerm) {
        super(COUNT_DIRECTIONAL);
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
