package de.hskempten.tabulang.items;

public class BinBoolItem implements LanguageItem {
    //"and"/"or"/"xor"/"iff"/"impl"
    private String myString;

    public BinBoolItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
