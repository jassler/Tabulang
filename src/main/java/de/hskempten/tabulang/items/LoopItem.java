package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.LOOP_LOOPBODY;
import static de.hskempten.tabulang.items.LanguageItemType.LOOP_STATEMENT;

public class LoopItem extends LanguageItemAbstract {
    //'for'
    private IdentifierItem myIdentifier;
    //'in'
    private TermItem myTerm;
    private LoopStmntItem myLoopStmnt;
    //'('
    private LoopBodyItem myLoopBody;
    //')'

    public LoopItem(IdentifierItem myIdentifier, TermItem myTerm, LoopStmntItem myLoopStmnt) {
        super(LOOP_STATEMENT);
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyLoopStmnt(myLoopStmnt);
    }

    public LoopItem(IdentifierItem myIdentifier, TermItem myTerm, LoopBodyItem myLoopBody) {
        super(LOOP_LOOPBODY);
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setMyLoopBody(myLoopBody);
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
}
