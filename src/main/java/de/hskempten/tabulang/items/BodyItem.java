package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class BodyItem extends LanguageItemAbstract {
    //'{'
    private ArrayList<StatementAnyItem> myStatements;
    //'}'

    public BodyItem(ArrayList<StatementAnyItem> myStatements) {
        this.setMyStatements(myStatements);
    }

    public ArrayList<StatementAnyItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(ArrayList<StatementAnyItem> myStatements) {
        this.myStatements = myStatements;
    }
}
