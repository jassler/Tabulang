package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class TermItem extends TermOrRItem {
    //'('
    private TermItem myTerm;
    //')'
    private TermRItem myTermR;
    private IdentifierItem myIdentifier;
    private LoopItem myLoop;
    private OrdinalItem myOrdinal;
    private DirectionalTermItem myDirectionalTerm;
    private FunDefItem myFunDef;
    private AggregationTItem myAggregationT;
    private DistinctTItem myDistinctT;
    private FunCallItem myFunCall;

    public TermItem(TermItem myTerm, TermRItem myTermR) {
        super(TERM_BRACKET);
        this.setMyTerm(myTerm);
        this.setMyTermR(myTermR);
    }

    public TermItem(TermRItem myTermR, IdentifierItem myIdentifier) {
        super(TERM_IDENTIFIER);
        this.setMyTermR(myTermR);
        this.setMyIdentifier(myIdentifier);
    }

    public TermItem(TermRItem myTermR, LoopItem myLoop) {
        super(TERM_LOOP);
        this.setMyTermR(myTermR);
        this.setMyLoop(myLoop);
    }

    public TermItem(TermRItem myTermR, OrdinalItem myOrdinal) {
        super(TERM_ORDINAL);
        this.setMyTermR(myTermR);
        this.setMyOrdinal(myOrdinal);
    }

    public TermItem(TermRItem myTermR, DirectionalTermItem myDirectionalTerm) {
        super(TERM_DIRECTIONAL);
        this.setMyTermR(myTermR);
        this.setMyDirectionalTerm(myDirectionalTerm);
    }

    public TermItem(TermRItem myTermR, FunDefItem myFunDef) {
        super(TERM_FUNDEF);
        this.setMyTermR(myTermR);
        this.setMyFunDef(myFunDef);
    }

    public TermItem(TermRItem myTermR, AggregationTItem myAggregationT) {
        super(TERM_AGGREGATION);
        this.setMyTermR(myTermR);
        this.setMyAggregationT(myAggregationT);
    }

    public TermItem(TermRItem myTermR, DistinctTItem myDistinctT) {
        super(TERM_DISTINCT);
        this.setMyTermR(myTermR);
        this.setMyDistinctT(myDistinctT);
    }

    public TermItem(TermRItem myTermR, FunCallItem myFunCall) {
        super(TERM_FUNCALL);
        this.setMyTermR(myTermR);
        this.setMyFunCall(myFunCall);
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
}
