package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class CountTItem implements LanguageItem {
    //"horizontal"/"vertical"
    private String myString;
    private TermItem myTerm;

    private LanguageItemType itemType;
    private TextPosition myTextPosition;

    public CountTItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.COUNT_EMPTY;
    }

    public CountTItem(String myString, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.COUNT_DIRECTIONAL;
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
