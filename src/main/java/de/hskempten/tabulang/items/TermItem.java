package de.hskempten.tabulang.items;

public class TermItem implements TermOrRItem {
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
    private FunCallItem myFunCall;

    private LanguageItemType itemType;

    public TermItem(TermItem myTerm, TermRItem myTermR) {
        this.setMyTerm(myTerm);
        this.setMyTermR(myTermR);
        this.itemType = LanguageItemType.TERM_BRACKET;
    }

    public TermItem(TermRItem myTermR, IdentifierItem myIdentifier) {
        this.setMyTermR(myTermR);
        this.setMyIdentifier(myIdentifier);
        this.itemType = LanguageItemType.TERM_IDENTIFIER;
    }

    public TermItem(TermRItem myTermR, LoopItem myLoop) {
        this.setMyTermR(myTermR);
        this.setMyLoop(myLoop);
        this.itemType = LanguageItemType.TERM_LOOP;
    }

    public TermItem(TermRItem myTermR, FlipTItem myFlipT) {
        this.setMyTermR(myTermR);
        this.setMyFlipT(myFlipT);
        this.itemType = LanguageItemType.TERM_FLIP;
    }

    public TermItem(TermRItem myTermR, OrdinalItem myOrdinal) {
        this.setMyTermR(myTermR);
        this.setMyOrdinal(myOrdinal);
        this.itemType = LanguageItemType.TERM_ORDINAL;
    }

    public TermItem(TermRItem myTermR, DirectionalTermItem myDirectionalTerm) {
        this.setMyTermR(myTermR);
        this.setMyDirectionalTerm(myDirectionalTerm);
        this.itemType = LanguageItemType.TERM_DIRECTIONAL;
    }

    public TermItem(TermRItem myTermR, FunDefItem myFunDef) {
        this.setMyTermR(myTermR);
        this.setMyFunDef(myFunDef);
        this.itemType = LanguageItemType.TERM_FUNDEF;
    }

    public TermItem(TermRItem myTermR, AggregationTItem myAggregationT) {
        this.setMyTermR(myTermR);
        this.setMyAggregationT(myAggregationT);
        this.itemType = LanguageItemType.TERM_AGGREGATION;
    }

    public TermItem(TermRItem myTermR, DistinctTItem myDistinctT) {
        this.setMyTermR(myTermR);
        this.setMyDistinctT(myDistinctT);
        this.itemType = LanguageItemType.TERM_DISTINCT;
    }

    public TermItem(TermRItem myTermR, FunCallItem myFunCall) {
        this.setMyTermR(myTermR);
        this.setMyFunCall(myFunCall);
        this.itemType = LanguageItemType.TERM_FUNCALL;
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

    public FunCallItem getMyFunCall() {
        return myFunCall;
    }

    public void setMyFunCall(FunCallItem myFunCall) {
        this.myFunCall = myFunCall;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
