package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class BodyItem implements LanguageItem {
    //'{'
    private ArrayList<StatementItem> myStatements;
    private TextPosition myTextPosition;
    //'}'

    public BodyItem(ArrayList<StatementItem> myStatements) {
        this.setMyStatements(myStatements);
    }

    public ArrayList<StatementItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(ArrayList<StatementItem> myStatements) {
        this.myStatements = myStatements;
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
