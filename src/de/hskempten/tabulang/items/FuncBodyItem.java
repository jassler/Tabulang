package de.hskempten.tabulang.items;

import java.util.ArrayList;
import java.util.LinkedList;

public class FuncBodyItem {
    private LinkedList<StatementItem> myStatements;
    private ArrayList<ReturnStmntItem> myReturnStmnts;
    private ReturnStmntItem myReturnStmnt;

    public FuncBodyItem(ReturnStmntItem myReturnStmnt) {
        this.setMyReturnStmnt(myReturnStmnt);
    }

    public FuncBodyItem(LinkedList<StatementItem> myStatements) {
        this.setMyStatements(myStatements);
    }

    public FuncBodyItem(ArrayList<ReturnStmntItem> myReturnStmnts) {
        this.myReturnStmnts = myReturnStmnts;
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
}
