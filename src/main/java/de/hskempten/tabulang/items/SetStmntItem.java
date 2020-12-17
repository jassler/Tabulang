package de.hskempten.tabulang.items;

public class SetStmntItem implements LanguageItem {
    private TermItem myTerm;
    private LanguageItemType itemType = LanguageItemType.ANYSTATEMENT_SET;

    public SetStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }


    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
