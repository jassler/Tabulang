package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.BinBoolItem;
import de.hskempten.tabulang.items.LanguageItemType;
import de.hskempten.tabulang.items.PredItem;
import de.hskempten.tabulang.items.PredRItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class PredRType implements LanguageType {

    public static PredRType instance = new PredRType();

    @Override
    public PredRItem parse(Lexer l) throws ParseTimeException {
        PredRItem item;

        BinBoolItem myBinBool;
        PredItem myPred;

        if ("binBool".equals(l.lookahead().getType())) {
            myBinBool = BinBoolType.instance.parse(l);
            myPred = PredType.instance.parse(l);
            item = new PredRItem(myBinBool, myPred);
        } else {
            item = new PredRItem();
            if ("bracket".equals(l.lookahead().getType())&& ")".equals(l.lookahead().getContent())){
                item.setLanguageItemType(LanguageItemType.PREDR_BRACKET);
            }
        }

        return item;
    }
}
