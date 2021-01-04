package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public class GroupStmntItem implements LanguageItem {
    private String myString;
    private GroupAreaItem myGroupArea;
    private TermItem myTerm;
    private FunCallItem myFunCall;

    private LanguageItemType itemType;
    private TextPosition myTextPositon;

    public GroupStmntItem(String myString, GroupAreaItem myGroupArea, TermItem myTerm, FunCallItem myFunCall) {
        this.setMyString(myString);
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
        this.itemType = LanguageItemType.GROUP_HIDING_AREA_FUNCALL;
    }

    public GroupStmntItem(String myString, GroupAreaItem myGroupArea, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.GROUP_HIDING_AREA;
    }

    public GroupStmntItem(String myString, TermItem myTerm, FunCallItem myFunCall) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
        this.itemType = LanguageItemType.GROUP_HIDING_FUNCALL;
    }

    public GroupStmntItem(String myString, TermItem myTerm) {
        this.setMyString(myString);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.GROUP_HIDING;
    }

    public GroupStmntItem(GroupAreaItem myGroupArea, TermItem myTerm, FunCallItem myFunCall) {
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
        this.itemType = LanguageItemType.GROUP_AREA_FUNCALL;
    }

    public GroupStmntItem(GroupAreaItem myGroupArea, TermItem myTerm) {
        this.setMyGroupArea(myGroupArea);
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.GROUP_AREA;
    }

    public GroupStmntItem(TermItem myTerm, FunCallItem myFunCall) {
        this.setMyTerm(myTerm);
        this.setMyFunCall(myFunCall);
        this.itemType = LanguageItemType.GROUP_FUNCALL;
    }

    public GroupStmntItem(TermItem myTerm) {
        this.setMyTerm(myTerm);
        this.itemType = LanguageItemType.GROUP_EMPTY;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public GroupAreaItem getMyGroupArea() {
        return myGroupArea;
    }

    public void setMyGroupArea(GroupAreaItem myGroupArea) {
        this.myGroupArea = myGroupArea;
    }

    public TermItem getMyTerm() {
        return myTerm;
    }

    public void setMyTerm(TermItem myTerm) {
        this.myTerm = myTerm;
    }

    public FunCallItem getMyFunCall() {
        return myFunCall;
    }

    public void setMyFunCall(FunCallItem myFunCall) {
        this.myFunCall = myFunCall;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPositon;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPositon = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
