package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.BinBoolItem;
import de.hskempten.tabulang.items.LanguageItemType;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.PredRItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class PredRType implements Parser {

    public static PredRType instance = new PredRType();

    @Override
    public PredRItem parse(Lexer l) throws ParseTimeException {
        PredRItem item;

        BinBoolItem myBinBool;
        PredItem myPred;

        TextPosition startP = l.lookahead().getPosition();
        if ("binBool".equals(l.lookahead().getType())) {
            myBinBool = BinBoolType.instance.parse(l);
            myPred = PredType.instance.parse(l);
            item = new PredRItem(myBinBool, myPred);
        } else {
            startP = l.lookbehind().getPosition();
            item = new PredRItem();
            if ("bracket".equals(l.lookahead().getType()) && ")".equals(l.lookahead().getContent())) {
                item.setLanguageItemType(LanguageItemType.PREDR_BRACKET);
            }
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
