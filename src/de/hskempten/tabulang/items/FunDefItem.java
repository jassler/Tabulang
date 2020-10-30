package de.hskempten.tabulang.items;

public class FunDefItem {
    private VListItem myVList;
    private IdentifierItem myIdentifier;
    private FuncBodyItem myFuncBody;
    private TermItem myTerm;

    public FunDefItem(VListItem myVList, FuncBodyItem myFuncBody) {
        this.setMyVList(myVList);
        this.setMyFuncBody(myFuncBody);
    }

    public FunDefItem(VListItem myVList, TermItem myTerm) {
        this.setMyVList(myVList);
        this.setMyTerm(myTerm);
    }

    public FunDefItem(IdentifierItem myIdentifier, FuncBodyItem myFuncBody) {
        this.setMyIdentifier(myIdentifier);
        this.setMyFuncBody(myFuncBody);
    }

    public FunDefItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
    }

    public VListItem getMyVList() {
        return myVList;
    }

    public void setMyVList(VListItem myVList) {
        this.myVList = myVList;
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public FuncBodyItem getMyFuncBody() {
        return myFuncBody;
    }

    public void setMyFuncBody(FuncBodyItem myFuncBody) {
        this.myFuncBody = myFuncBody;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
