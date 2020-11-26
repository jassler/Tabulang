package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.AggregationTItem;
import de.hskempten.tabulang.items.AverageTItem;
import de.hskempten.tabulang.items.CountTItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class AggregationTType implements LanguageType {

    public static AggregationTType instance = new AggregationTType();

    @Override
    public AggregationTItem parse(Lexer l) throws ParseTimeException {
        AggregationTItem item;

        CountTItem myCountT;
        AverageTItem myAverageT;

        switch (l.lookahead().getContent()) {
            case "count" -> {
                myCountT = CountTType.instance.parse(l);
                item = new AggregationTItem(myCountT);
            }
            case "average" -> {
                myAverageT = AverageTType.instance.parse(l);
                item = new AggregationTItem(myAverageT);
            }
            default -> {
                throw new ParseTimeException(l, "Illegal start for AggregationTType: " + l.lookahead().getContent());
            }
        }


        return item;
    }
}
