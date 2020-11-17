package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.LoopBodyItem;
import de.hskempten.tabulang.items.LoopStmntItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

import java.util.ArrayList;

public class LoopBodyType implements LanguageType {

    public static LoopBodyType instance = new LoopBodyType();

    @Override
    public LoopBodyItem parse(Lexer l) throws ParseTimeException {
        LoopBodyItem item;

        ArrayList<LoopStmntItem> myLoopStmnts = new ArrayList<LoopStmntItem>();

        while (!("bracket".equals(l.lookahead().getType()) && "}".equals(l.lookahead().getContent()))) {
            myLoopStmnts.add(LoopStmntType.instance.parse(l));
        }

        item = new LoopBodyItem(myLoopStmnts);

        return item;
    }
}
