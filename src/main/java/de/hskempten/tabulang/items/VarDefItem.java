package de.hskempten.tabulang.items;

public class VarDefItem implements StatementAST {
    private VarDefAST myAssignmentOrProceduralF;

    public VarDefItem(IdentifierItem myIdentifier, TermItem myTerm) {
        this.setAssignmentOrProceduralF(new Assignment(myIdentifier, myTerm));
    }

    public VarDefItem(ProceduralFItem myProceduralF) {
        this.setAssignmentOrProceduralF(myProceduralF);
    }

    public VarDefAST getAssignmentOrProceduralF() {
        return myAssignmentOrProceduralF;
    }

    public void setAssignmentOrProceduralF(VarDefAST varDefOrProceduralF) {
        this.myAssignmentOrProceduralF = varDefOrProceduralF;
    }
}
