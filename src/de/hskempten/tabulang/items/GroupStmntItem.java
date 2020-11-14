package de.hskempten.tabulang.items;

public class GroupStmntItem implements LanguageItem {
    private String myString;
    private GroupAreaItem myGroupAream;
    private TermItem myTerm;
    private FunCallItem myFunCall;

    public GroupStmntItem(String myString, GroupAreaItem myGroupAream, TermItem myTerm, FunCallItem myFunCall) {
        this.setMyString(myString);
        this.setMyGroupAream(myGroupAream);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(String myString, GroupAreaItem myGroupAream, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyGroupAream(myGroupAream);
        this.setMyTerm(myTerm);
    }

    public GroupStmntItem(String myString, TermItem myTerm, FunCallItem myFunCall) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(String myString, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
    }

    public GroupStmntItem(GroupAreaItem myGroupAream, TermItem myTerm, FunCallItem myFunCall) {
        this.setMyGroupAream(myGroupAream);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(GroupAreaItem myGroupAream, TermItem myTerm) {
        this.setMyGroupAream(myGroupAream);
        this.setMyTerm(myTerm);
    }

    public GroupStmntItem(TermItem myTerm, FunCallItem myFunCall) {
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
    }

    public GroupStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public GroupAreaItem getMyGroupAream() {
        return myGroupAream;
    }

    public void setMyGroupAream(GroupAreaItem myGroupAream) {
        this.myGroupAream = myGroupAream;
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
