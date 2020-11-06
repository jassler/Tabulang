package de.hskempten.tabulang.items;

public class VarDefItem {
    private IdentifierItem myIdentifier;
    //':='
    private TermItem myTerm;
    //';'
    private ProceduralFItem myProceduralF;

    public VarDefItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setMyIdentifier(myIdentifier);
        this.setMyTerm(myTerm);
    }

    public VarDefItem(ProceduralFItem myProceduralF) {
        this.setMyProceduralF(myProceduralF);
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
