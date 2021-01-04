package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class ProceduralFItem implements LanguageItem {
    private IdentifierItem myIdentifier;
    private VListItem myVList;
    private FuncBodyItem myFuncBody;
    private TermItem myTerm;

    LanguageItemType itemType;
    private TextPosition myTextPosition;

    public ProceduralFItem(IdentifierItem myIdentifier, VListItem myVList, FuncBodyItem myFuncBody) {
        this.setMyIdentifier(myIdentifier);
        this.setMyVList(myVList);
        this.setMyFuncBody(myFuncBody);
        this.itemType = LanguageItemType.PROCEDURALF_FUNCBODY;
    }

    public ProceduralFItem(IdentifierItem myIdentifier, VListItem myVList, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyVList(myVList);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.PROCEDURALF_TERM;
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

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }
    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
