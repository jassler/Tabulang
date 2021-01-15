package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.PROCEDURALF_FUNCBODY;
import static de.hskempten.tabulang.items.LanguageItemType.PROCEDURALF_TERM;

public class ProceduralFItem extends LanguageItemAbstract {
    private IdentifierItem myIdentifier;
    private VListItem myVList;
    private FuncBodyItem myFuncBody;
    private TermItem myTerm;

    public ProceduralFItem(IdentifierItem myIdentifier, VListItem myVList, FuncBodyItem myFuncBody) {
        super(PROCEDURALF_FUNCBODY);
        this.setMyIdentifier(myIdentifier);
        this.setMyVList(myVList);
        this.setMyFuncBody(myFuncBody);
    }

    public ProceduralFItem(IdentifierItem myIdentifier, VListItem myVList, TermItem myTerm) {
        super(PROCEDURALF_TERM);
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
