package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.MARK_WITHIF;
import static de.hskempten.tabulang.items.LanguageItemType.MARK_WITHOUTIF;

public class MarkStmntItem extends LanguageItemAbstract {
    private TermItem myTerm;
    private TermItem mySecondTerm;
    private PredItem myPred;

    public MarkStmntItem(TermItem myTerm, TermItem mySecondTerm, PredItem myPred) {
        super(MARK_WITHIF);
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
        this.setMyPred(myPred);
    }

    public MarkStmntItem(TermItem myTerm, TermItem mySecondTerm) {
        super(MARK_WITHOUTIF);
        this.setMyTerm(myTerm);
        this.setMySecondTerm(mySecondTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public TermItem getMySecondTerm() {
        return mySecondTerm;
    }

    public void setMySecondTerm(TermItem mySecondTerm) {
        this.mySecondTerm = mySecondTerm;
    }

    public PredItem getMyPred() {
        return myPred;
    }

    public void setMyPred(PredItem myPred) {
        this.myPred = myPred;
    }
}
