package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class LoopItem implements LanguageItem {
    //'for'
    private IdentifierItem myIdentifier;
    //'in'
    private TermItem myTerm;
    private LoopStmntItem myLoopStmnt;
    //'('
    private LoopBodyItem myLoopBody;
    //')'

    private LanguageItemType itemType;
    private TextPosition myTextPositon;

    public LoopItem(IdentifierItem myIdentifier, TermItem myTerm, LoopStmntItem myLoopStmnt) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyLoopStmnt(myLoopStmnt);
        this.itemType = LanguageItemType.LOOP_STATEMENT;
    }

    public LoopItem(IdentifierItem myIdentifier, TermItem myTerm, LoopBodyItem myLoopBody) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyLoopBody(myLoopBody);
        this.itemType = LanguageItemType.LOOP_LOOPBODY;
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public LoopStmntItem getMyLoopStmnt() {
        return myLoopStmnt;
    }

    public void setMyLoopStmnt(LoopStmntItem myLoopStmnt) {
        this.myLoopStmnt = myLoopStmnt;
    }

    public LoopBodyItem getMyLoopBody() {
        return myLoopBody;
    }

    public void setMyLoopBody(LoopBodyItem myLoopBody) {
        this.myLoopBody = myLoopBody;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPositon;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPositon = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
