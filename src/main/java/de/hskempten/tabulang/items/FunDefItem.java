package de.hskempten.tabulang.items;

public class FunDefItem implements LanguageItem {
    private VListItem myVList;
    private IdentifierItem myIdentifier;
    private FuncBodyItem myFuncBody;
    private TermItem myTerm;

    private LanguageItemType itemType;

    public FunDefItem(VListItem myVList, FuncBodyItem myFuncBody) {
        this.setMyVList(myVList);
        this.setMyFuncBody(myFuncBody);
        this.itemType = LanguageItemType.FUNDEF_VLIST_FUNCBODY;
    }

    public FunDefItem(VListItem myVList, TermItem myTerm) {
        this.setMyVList(myVList);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.FUNDEF_VLIST_TERM;
    }

    public FunDefItem(IdentifierItem myIdentifier, FuncBodyItem myFuncBody) {
        this.setMyIdentifier(myIdentifier);
        this.setMyFuncBody(myFuncBody);
        this.itemType = LanguageItemType.FUNDEF_IDENTIFIER_FUNCBODY;
    }

    public FunDefItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.FUNDEF_IDENTIFIER_TERM;
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

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
