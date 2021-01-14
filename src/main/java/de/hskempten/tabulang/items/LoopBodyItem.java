package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class LoopBodyItem extends LanguageItemAbstract implements LanguageItem {
    private ArrayList<LoopStmntItem> myLoopStmnts;

    public LoopBodyItem(ArrayList<LoopStmntItem> myLoopStmnts) {
        this.setMyLoopStmnts(myLoopStmnts);
    }

    public ArrayList<LoopStmntItem> getMyLoopStmnts() {
        return myLoopStmnts;
    }

    public void setMyLoopStmnts(ArrayList<LoopStmntItem> myLoopStmnts) {
        this.myLoopStmnts = myLoopStmnts;
    }
}
