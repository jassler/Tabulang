package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class VarDefItem implements LanguageItem {
    private boolean isNewAssignment;
    private IdentifierItem myIdentifier;
    //':='
    private TermItem myTerm;
    //';'
    private ProceduralFItem myProceduralF;

    private LanguageItemType itemType;
    private TextPosition myTextPosition;

    public VarDefItem(IdentifierItem myIdentifier, TermItem myTerm, boolean isNewAssignment) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
        this.setNewAssignment(isNewAssignment);
        this.itemType = LanguageItemType.VARDEF_ASSIGNMENT;
    }

    public VarDefItem(ProceduralFItem myProceduralF) {
        this.setMyProceduralF(myProceduralF);
        this.itemType = LanguageItemType.VARDEF_PROCEDURALF;
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
