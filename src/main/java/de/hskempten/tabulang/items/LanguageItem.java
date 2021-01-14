package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

public interface LanguageItem {

    LanguageItemType getLanguageItemType();

    TextPosition getTextPosition();

    void setTextPosition(TextPosition textPosition);

    /*
    default public LanguageItemType getLanguageItemType() {
        return LanguageItemType.NULL;
    }

    default public TextPosition getTextPosition() {
        try {
            throw new ParseTimeException("Not implemented getTextPosition");
        } catch (ParseTimeException e) {
            e.printStackTrace();
        }
        return null;
    }



    default void setTextPosition(TextPosition textPosition) {
        try {
            throw new ParseTimeException("Not implemented setTextPosition:", textPosition);
        } catch (ParseTimeException e) {
            e.printStackTrace();
        }
    }
    */
}
