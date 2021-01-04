package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class LoopBodyItem implements LanguageItem {
    private ArrayList<LoopStmntItem> myLoopStmnts;
    private TextPosition myTextPosition;

    public LoopBodyItem(ArrayList<LoopStmntItem> myLoopStmnts) {
        this.setMyLoopStmnts(myLoopStmnts);
    }

    public ArrayList<LoopStmntItem> getMyLoopStmnts() {
        return myLoopStmnts;
    }

    public void setMyLoopStmnts(ArrayList<LoopStmntItem> myLoopStmnts) {
        this.myLoopStmnts = myLoopStmnts;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

}
