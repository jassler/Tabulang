package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class TupelItem {
    private TermItem myTerm;
    private ArrayList<TermItem> myTerms;
    private IntervallItem myIntervall;

    public TupelItem() {
    }

    public TupelItem(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public TupelItem(TermItem myTerm, ArrayList<TermItem> myTerms) {
        this.myTerm = myTerm;
        this.myTerms = myTerms;
    }

    public TupelItem(IntervallItem myIntervall) {
        this.myIntervall = myIntervall;
    }
}
