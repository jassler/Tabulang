package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class ProgramItem implements LanguageItem{
    private ArrayList<StatementItem> myStatements;

    public ProgramItem(ArrayList<StatementItem> myStatements) {
        this.myStatements = myStatements;
    }

    public ArrayList<StatementItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(ArrayList<StatementItem> myStatements) {
        this.myStatements = myStatements;
    }
}
