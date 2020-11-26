package de.hskempten.tabulang.items;

public class OperatorItem implements LanguageItem {
    //+ - * / div mod ^
    private String myString;

    public OperatorItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
