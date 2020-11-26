package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.ExistsPredItem;
import de.hskempten.tabulang.items.ForallPredItem;
import de.hskempten.tabulang.items.PQuantifiedItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.types.LanguageType;

public class PQuantifiedType implements LanguageType {

    public static PQuantifiedType instance = new PQuantifiedType();

    @Override
    public PQuantifiedItem parse(Lexer l) throws ParseTimeException {
        PQuantifiedItem item;

        ExistsPredItem myExistsPred;
        ForallPredItem myForallPred;

        if ("keyword".equals(l.lookahead().getType())) {
            switch (l.lookahead().getContent()) {
                case "exists" -> {
                    myExistsPred = ExistsPredType.instance.parse(l);
                    item = new PQuantifiedItem(myExistsPred);
                }
                case "forAll" -> {
                    myForallPred = ForallPredType.instance.parse(l);
                    item = new PQuantifiedItem(myForallPred);
                }
                default -> {
                    throw new ParseTimeException(l, "Expected keyword 'exists' or 'forAll', but got: " + l.lookahead().getContent());
                }
            }
        } else {
            throw new ParseTimeException(l, "Expected keyword 'exists' or 'forAll', but got: " + l.lookahead().getContent());
        }

        return item;
    }
}
