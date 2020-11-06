package de.hskempten.tabulang.items;

public class TermItem {
    //'('
    private TermItem myTerm;
    //')'
    private TermRItem myTermR;
    private IdentifierItem myIdentifier;
    private LoopItem myLoop;
    private FlipTItem myFlipT;
    private OrdinalItem myOrdinal;
    private DirectionalTermItem myDirectionalTerm;
    private FunDefItem myFunDef;
    private AggregationTItem myAggregationT;
    private DistinctTItem myDistinctT;

    public TermItem(TermItem myTerm, TermRItem myTermR) {
        this.setMyTerm(myTerm);
        this.setMyTermR(myTermR);
    }

    public TermItem(TermRItem myTermR, IdentifierItem myIdentifier) {
        this.setMyTermR(myTermR);
        this.setMyIdentifier(myIdentifier);
    }

    public TermItem(TermRItem myTermR, LoopItem myLoop) {
        this.setMyTermR(myTermR);
        this.setMyLoop(myLoop);
    }

    public TermItem(TermRItem myTermR, FlipTItem myFlipT) {
        this.setMyTermR(myTermR);
        this.setMyFlipT(myFlipT);
    }

    public TermItem(TermRItem myTermR, OrdinalItem myOrdinal) {
        this.setMyTermR(myTermR);
        this.setMyOrdinal(myOrdinal);
    }

    public TermItem(TermRItem myTermR, DirectionalTermItem myDirectionalTerm) {
        this.setMyTermR(myTermR);
        this.setMyDirectionalTerm(myDirectionalTerm);
    }

    public TermItem(TermRItem myTermR, FunDefItem myFunDef) {
        this.setMyTermR(myTermR);
        this.setMyFunDef(myFunDef);
    }

    public TermItem(TermRItem myTermR, AggregationTItem myAggregationT) {
        this.setMyTermR(myTermR);
        this.setMyAggregationT(myAggregationT);
    }

    public TermItem(TermRItem myTermR, DistinctTItem myDistinctT) {
        this.setMyTermR(myTermR);
        this.setMyDistinctT(myDistinctT);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public TermRItem getMyTermR() {
        return myTermR;
    }

    public void setMyTermR(TermRItem myTermR) {
        this.myTermR = myTermR;
    }

    public IdentifierItem getMyIdentifier() {
        return myIdentifier;
    }

    public void setMyIdentifier(IdentifierItem myIdentifier) {
        this.myIdentifier = myIdentifier;
    }

    public LoopItem getMyLoop() {
        return myLoop;
    }

    public void setMyLoop(LoopItem myLoop) {
        this.myLoop = myLoop;
    }

    public FlipTItem getMyFlipT() {
        return myFlipT;
    }

    public void setMyFlipT(FlipTItem myFlipT) {
        this.myFlipT = myFlipT;
    }

    public OrdinalItem getMyOrdinal() {
        return myOrdinal;
    }

    public void setMyOrdinal(OrdinalItem myOrdinal) {
        this.myOrdinal = myOrdinal;
    }

    public DirectionalTermItem getMyDirectionalTerm() {
        return myDirectionalTerm;
    }

    public void setMyDirectionalTerm(DirectionalTermItem myDirectionalTerm) {
        this.myDirectionalTerm = myDirectionalTerm;
    }

    public FunDefItem getMyFunDef() {
        return myFunDef;
    }

    public void setMyFunDef(FunDefItem myFunDef) {
        this.myFunDef = myFunDef;
    }

    public AggregationTItem getMyAggregationT() {
        return myAggregationT;
    }

    public void setMyAggregationT(AggregationTItem myAggregationT) {
        this.myAggregationT = myAggregationT;
    }

    public DistinctTItem getMyDistinctT() {
        return myDistinctT;
    }

    public void setMyDistinctT(DistinctTItem myDistinctT) {
        this.myDistinctT = myDistinctT;
    }
}
