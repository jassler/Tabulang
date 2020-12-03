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

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
