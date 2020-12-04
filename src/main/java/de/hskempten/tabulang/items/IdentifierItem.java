package de.hskempten.tabulang.items;

public class IdentifierItem implements LanguageItem {
    private String myString; //[a-zA-Z][0-9a-zA-Z]*

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
    public LanguageItemType getLanguageItemType() {
        return LanguageItemType.STATEMENT_IDENTIFIER;
    }
}
