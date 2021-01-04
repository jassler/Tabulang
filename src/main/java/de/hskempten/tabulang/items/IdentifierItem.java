package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class IdentifierItem implements LanguageItem {
    private String myString; //[a-zA-Z][0-9a-zA-Z]*
    private TextPosition myTextPositon;

    public IdentifierItem(String myString) {
        this.myString = myString;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
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
        return LanguageItemType.STATEMENT_IDENTIFIER;
    }
}
