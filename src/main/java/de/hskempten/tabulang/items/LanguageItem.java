package de.hskempten.tabulang.items;

public interface LanguageItem {

    default public LanguageItemType getLanguageItemType(){
        return LanguageItemType.NULL;
    }
}
