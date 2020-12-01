package de.hskempten.tabulang.items;

public class ProceduralFItem implements ProceduralFAST {
    private IdentifierItem myIdentifier;
    private VListItem myVList;
    private FuncBodyAST myFuncBodyOrTerm;
    private FuncBodyItem myFuncBody;
    private TermItem myTerm;

    public ProceduralFItem(IdentifierItem myIdentifier, VListItem myVList, FuncBodyItem myFuncBody) {
        this.setMyIdentifier(myIdentifier);
        this.setMyVList(myVList);
        this.setMyFuncBody(myFuncBody);
    }

    public ProceduralFItem(IdentifierItem myIdentifier, VListItem myVList, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyVList(myVList);
        this.setMyTerm(myTerm);
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public VListItem getMyVList() {
        return myVList;
    }

    public void setMyVList(VListItem myVList) {
        this.myVList = myVList;
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
