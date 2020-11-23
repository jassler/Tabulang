package de.hskempten.tabulang.items;

public class AggregationTItem implements LanguageItem {
    private CountTItem myCountT;
    private AverageTItem myAverageT;

    public AggregationTItem(CountTItem myCountT) {
        this.setMyCountT(myCountT);
    }

    public AggregationTItem(AverageTItem myAverageT) {
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
