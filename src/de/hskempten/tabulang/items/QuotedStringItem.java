package de.hskempten.tabulang.items;

public class QuotedStringItem {
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
}
