package de.hskempten.tabulang.items;

public class BinRelSymItem implements LanguageItem {
    private String myString;

    public BinRelSymItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
