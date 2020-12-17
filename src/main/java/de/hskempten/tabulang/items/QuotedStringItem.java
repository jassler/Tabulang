package de.hskempten.tabulang.items;

public class QuotedStringItem implements LanguageItem {
    //any char except the quote char
    private String myString;

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
    public LanguageItemType getLanguageItemType() {
        return LanguageItemType.ORDINAL_QUOTEDSTRING;
    }
}
