package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class FunDefItem extends LanguageItemAbstract {
    private VListItem myVList;
    private IdentifierItem myIdentifier;
    private FuncBodyItem myFuncBody;
    private TermItem myTerm;

    public FunDefItem(VListItem myVList, FuncBodyItem myFuncBody) {
        super(FUNDEF_VLIST_FUNCBODY);
        this.setMyVList(myVList);
        this.setMyFuncBody(myFuncBody);
    }

    public FunDefItem(VListItem myVList, TermItem myTerm) {
        super(FUNDEF_VLIST_TERM);
        this.setMyVList(myVList);
        this.setMyTerm(myTerm);
    }

    public FunDefItem(IdentifierItem myIdentifier, FuncBodyItem myFuncBody) {
        super(FUNDEF_IDENTIFIER_FUNCBODY);
        this.setMyIdentifier(myIdentifier);
        this.setMyFuncBody(myFuncBody);
    }

    public FunDefItem(IdentifierItem myIdentifier, TermItem myTerm) {
        super(FUNDEF_IDENTIFIER_TERM);
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
