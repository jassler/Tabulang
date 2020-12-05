package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class TupelItem implements LanguageItem {
    private TermItem myTerm;
    private ArrayList<TermItem> myTerms;
    private IntervallItem myIntervall;

    private LanguageItemType itemType;

    public TupelItem() {
        this.itemType = LanguageItemType.TUPEL_EMPTY;
    }

    public TupelItem(TermItem myTerm) {
        this.myTerm = myTerm;
        this.itemType = LanguageItemType.TUPEL_ONE;
    }

    public TupelItem(TermItem myTerm, ArrayList<TermItem> myTerms) {
        this.myTerm = myTerm;
        this.myTerms = myTerms;
        this.itemType = LanguageItemType.TUPEL_MULTI;
    }

    public TupelItem(IntervallItem myIntervall) {
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

    public LanguageItemType getItemType() {
        return itemType;
    }

    public void setItemType(LanguageItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
