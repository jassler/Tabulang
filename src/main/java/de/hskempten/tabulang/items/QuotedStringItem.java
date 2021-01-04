package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class QuotedStringItem implements LanguageItem {
    //any char except the quote char
    private String myString;
    private TextPosition myTextPosition;

    public QuotedStringItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
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
        return LanguageItemType.ORDINAL_QUOTEDSTRING;
    }
}
