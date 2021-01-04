package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class TupelItem implements LanguageItem {
    private TermItem myTerm;
    private ArrayList<TermItem> myTerms;
    private IntervallItem myIntervall;

    private LanguageItemType itemType;
    private TextPosition myTextPositon;

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
        this.itemType = LanguageItemType.TUPEL_INTERVAL;
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
    public TextPosition getTextPosition() {
        return myTextPositon;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPositon = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
