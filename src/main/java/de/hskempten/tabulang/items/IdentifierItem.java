package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.STATEMENT_IDENTIFIER;

public class IdentifierItem extends LanguageItemAbstract {
    private String myString; //[a-zA-Z][0-9a-zA-Z]*

    public IdentifierItem(String myString) {
        super(STATEMENT_IDENTIFIER);
        this.myString = myString;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
