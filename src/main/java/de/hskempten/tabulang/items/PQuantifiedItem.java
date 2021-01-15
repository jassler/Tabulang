package de.hskempten.tabulang.items;

import static de.hskempten.tabulang.items.LanguageItemType.QUANTIFIED_EXISTS;
import static de.hskempten.tabulang.items.LanguageItemType.QUANTIFIED_FORALL;

public class PQuantifiedItem extends LanguageItemAbstract {
    private ExistsPredItem myExistsPred;
    private ForallPredItem myForallPred;

    public PQuantifiedItem(ExistsPredItem myExistsPred) {
        super(QUANTIFIED_EXISTS);
        this.setMyExistsPred(myExistsPred);
    }

    public PQuantifiedItem(ForallPredItem myForallPred) {
        super(QUANTIFIED_FORALL);
        this.setMyForallPred(myForallPred);
    }

    public ExistsPredItem getMyExistsPred() {
        return myExistsPred;
    }

    public void setMyExistsPred(ExistsPredItem myExistsPred) {
        this.myExistsPred = myExistsPred;
    }

    public ForallPredItem getMyForallPred() {
        return myForallPred;
    }

    public void setMyForallPred(ForallPredItem myForallPred) {
        this.myForallPred = myForallPred;
    }
}
