package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class AggregationTItem implements LanguageItem {
    private CountTItem myCountT;
    private AverageTItem myAverageT;

    private LanguageItemType itemType;
    private TextPosition myTextPositon;

    public AggregationTItem(CountTItem myCountT) {
        this.setMyCountT(myCountT);
        this.itemType = LanguageItemType.AGGREGATION_COUNT;
    }

    public AggregationTItem(AverageTItem myAverageT) {
        this.setMyAverageT(myAverageT);
        this.itemType = LanguageItemType.AGGREGATION_AVERAGE;
    }

    public CountTItem getMyCountT() {
        return myCountT;
    }

    public void setMyCountT(CountTItem myCountT) {
        this.myCountT = myCountT;
    }

    public AverageTItem getMyAverageT() {
        return myAverageT;
    }

    public void setMyAverageT(AverageTItem myAverageT) {
        this.myAverageT = myAverageT;
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
