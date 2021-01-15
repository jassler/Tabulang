package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class GroupStmntItem extends LanguageItemAbstract {
    private String myString;
    private GroupAreaItem myGroupArea;
    private TermItem myTerm;
    private FunCallItem myFunCall;

    public GroupStmntItem(String myString, GroupAreaItem myGroupArea, TermItem myTerm, FunCallItem myFunCall) {
        super(GROUP_HIDING_AREA_FUNCALL);
        this.setMyString(myString);
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(String myString, GroupAreaItem myGroupArea, TermItem myTerm) {
        super(GROUP_HIDING_AREA);
        this.setMyString(myString);
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
    }

    public GroupStmntItem(String myString, TermItem myTerm, FunCallItem myFunCall) {
        super(GROUP_HIDING_FUNCALL);
        this.setMyString(myString);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(String myString, TermItem myTerm) {
        super(GROUP_HIDING);
        this.setMyString(myString);
        this.setMyTerm(myTerm);
    }

    public GroupStmntItem(GroupAreaItem myGroupArea, TermItem myTerm, FunCallItem myFunCall) {
        super(GROUP_AREA_FUNCALL);
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(GroupAreaItem myGroupArea, TermItem myTerm) {
        super(GROUP_AREA);
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
    }

    public GroupStmntItem(TermItem myTerm, FunCallItem myFunCall) {
        super(GROUP_FUNCALL);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(TermItem myTerm) {
        super(GROUP_EMPTY);
        this.setMyTerm(myTerm);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public GroupAreaItem getMyGroupArea() {
        return myGroupArea;
    }

    public void setMyGroupArea(GroupAreaItem myGroupArea) {
        this.myGroupArea = myGroupArea;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public FunCallItem getMyFunCall() {
        return myFunCall;
    }

    public void setMyFunCall(FunCallItem myFunCall) {
        this.myFunCall = myFunCall;
    }
}
