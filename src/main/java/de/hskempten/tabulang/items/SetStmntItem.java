package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.ANYSTATEMENT_SET;

public class SetStmntItem extends LanguageItemAbstract {
    private TermItem myTerm;

    public SetStmntItem(TermItem myTerm) {
        super(ANYSTATEMENT_SET);
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }
}
