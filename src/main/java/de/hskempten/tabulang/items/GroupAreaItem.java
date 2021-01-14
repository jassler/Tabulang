package de.hskempten.tabulang.items;

public class GroupAreaItem extends LanguageItemAbstract implements LanguageItem {
    //"before"/"after"
    private String myString;

    public GroupAreaItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
