package de.hskempten.tabulang.items;

import java.util.ArrayList;
import java.util.LinkedList;

public class FuncBodyItem implements LanguageItem {
    private LinkedList<StatementItem> myStatements;
    private ArrayList<ReturnStmntItem> myReturnStmnts;
    private ReturnStmntItem myReturnStmnt;

    private LanguageItemType itemType;

    //TODO there should be a constructor with statements AND returnStatements

    public FuncBodyItem(ReturnStmntItem myReturnStmnt) {
        this.setMyReturnStmnt(myReturnStmnt);
        this.itemType = LanguageItemType.FUNCBODY_RETURN;
    }

    public FuncBodyItem(LinkedList<StatementItem> myStatements) {
        this.setMyStatements(myStatements);
        this.itemType = LanguageItemType.FUNCBODY_STATEMENTS;
    }

    public FuncBodyItem(ArrayList<ReturnStmntItem> myReturnStmnts) {
        this.myReturnStmnts = myReturnStmnts;
        this.itemType = LanguageItemType.FUNCBODY_RETURNS;
    }


    public ArrayList<ReturnStmntItem> getMyReturnStmnts() {
        return myReturnStmnts;
    }

    public void setMyReturnStmnts(ArrayList<ReturnStmntItem> myReturnStmnts) {
        this.myReturnStmnts = myReturnStmnts;
    }

    public ReturnStmntItem getMyReturnStmnt() {
        return myReturnStmnt;
    }

    public void setMyReturnStmnt(ReturnStmntItem myReturnStmnt) {
        this.myReturnStmnt = myReturnStmnt;
    }

    public LinkedList<StatementItem> getMyStatements() {
        return myStatements;
    }

    public void setMyStatements(LinkedList<StatementItem> myStatements) {
        this.myStatements = myStatements;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
