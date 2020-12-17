package de.hskempten.tabulang.items;

public class AggregationTItem implements LanguageItem {
    private CountTItem myCountT;
    private AverageTItem myAverageT;

    private LanguageItemType itemType;

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
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
