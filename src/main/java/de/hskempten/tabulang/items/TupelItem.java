package de.hskempten.tabulang.items;

import java.util.ArrayList;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class TupelItem extends LanguageItemAbstract implements LanguageItem {
    private TermItem myTerm;
    private ArrayList<TermItem> myTerms;
    private IntervallItem myIntervall;

    public TupelItem() {
        super(TUPEL_EMPTY);
    }

    public TupelItem(TermItem myTerm) {
        super(TUPEL_ONE);
        this.myTerm = myTerm;
    }

    public TupelItem(TermItem myTerm, ArrayList<TermItem> myTerms) {
        super(TUPEL_MULTI);
        this.myTerm = myTerm;
        this.myTerms = myTerms;
    }

    public TupelItem(IntervallItem myIntervall) {
        super(TUPEL_INTERVAL);
        this.myIntervall = myIntervall;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public ArrayList<TermItem> getMyTerms() {
        return myTerms;
    }

    public void setMyTerms(ArrayList<TermItem> myTerms) {
        this.myTerms = myTerms;
    }

    public IntervallItem getMyIntervall() {
        return myIntervall;
    }

    public void setMyIntervall(IntervallItem myIntervall) {
        this.myIntervall = myIntervall;
    }
}
