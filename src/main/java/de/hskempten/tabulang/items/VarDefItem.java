package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.VARDEF_ASSIGNMENT;
import static de.hskempten.tabulang.items.LanguageItemType.VARDEF_PROCEDURALF;

public class VarDefItem extends LanguageItemAbstract {
    private boolean isNewAssignment;
    private IdentifierItem myIdentifier;
    //':='
    private TermItem myTerm;
    //';'
    private ProceduralFItem myProceduralF;

    public VarDefItem(IdentifierItem myIdentifier, TermItem myTerm, boolean isNewAssignment) {
        super(VARDEF_ASSIGNMENT);
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setNewAssignment(isNewAssignment);
    }

    public VarDefItem(ProceduralFItem myProceduralF) {
        super(VARDEF_PROCEDURALF);
        this.setMyProceduralF(myProceduralF);
    }

    public boolean isNewAssignment() {
        return isNewAssignment;
    }

    public void setNewAssignment(boolean newAssignment) {
        isNewAssignment = newAssignment;
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

    public ProceduralFItem getMyProceduralF() {
        return myProceduralF;
    }

    public void setMyProceduralF(ProceduralFItem myProceduralF) {
        this.myProceduralF = myProceduralF;
    }
}
