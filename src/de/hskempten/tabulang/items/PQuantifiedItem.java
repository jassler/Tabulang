package de.hskempten.tabulang.items;

public class PQuantifiedItem {
    private ExistsPredItem myExistsPred;
    private ForallPredItem myForallPred;

    public PQuantifiedItem(ExistsPredItem myExistsPred) {
        this.setMyExistsPred(myExistsPred);
    }

    public PQuantifiedItem(ForallPredItem myForallPred) {
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
