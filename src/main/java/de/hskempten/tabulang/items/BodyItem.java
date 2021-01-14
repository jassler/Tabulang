package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class BodyItem extends LanguageItemAbstract implements LanguageItem {
    //'{'
    private ArrayList<StatementItem> myStatements;
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
}
