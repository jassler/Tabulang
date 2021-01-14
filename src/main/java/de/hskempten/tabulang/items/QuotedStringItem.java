package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import static de.hskempten.tabulang.items.LanguageItemType.ORDINAL_QUOTEDSTRING;

public class QuotedStringItem extends LanguageItemAbstract implements LanguageItem {
    //any char except the quote char
    private String myString;

    public QuotedStringItem(String myString) {
        super(ORDINAL_QUOTEDSTRING);
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
