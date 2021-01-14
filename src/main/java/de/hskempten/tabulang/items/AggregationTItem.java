package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.AGGREGATION_AVERAGE;
import static de.hskempten.tabulang.items.LanguageItemType.AGGREGATION_COUNT;

public class AggregationTItem extends LanguageItemAbstract implements LanguageItem {
    private CountTItem myCountT;
    private AverageTItem myAverageT;

    public AggregationTItem(CountTItem myCountT) {
        super(AGGREGATION_COUNT);
        this.setMyCountT(myCountT);
    }

    public AggregationTItem(AverageTItem myAverageT) {
        super(AGGREGATION_AVERAGE);
        this.setMyAverageT(myAverageT);
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
}
