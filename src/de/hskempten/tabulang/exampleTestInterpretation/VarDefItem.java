package de.hskempten.tabulang.exampleTestInterpretation;


import de.hskempten.tabulang.items.ProceduralFItem;


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

    public Interpretation eval(Interpretation i){
        if(myProceduralF == null){
            myIdentifier.eval(i);
            myTerm.eval(i);
            System.out.println("Gerade evaluiert: " + this.getClass().getSimpleName());
            return i;
        }
        System.out.println(this.getClass().getSimpleName() + "hmmmmm");
        return i;
    }
}
