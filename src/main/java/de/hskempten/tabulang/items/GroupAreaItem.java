package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class GroupAreaItem implements LanguageItem {
    //"before"/"after"
    private String myString;
    private TextPosition myTextPosition;

    public GroupAreaItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

}
