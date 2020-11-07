package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class StatementType implements Parser {

    public static StatementType instance = new StatementType();

    public StatementItem parse(Lexer l) throws ParseTimeException {

        StatementItem statement;

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                switch (l.lookahead().getContent()) {
                    case "for" -> statement = new StatementItem(LoopType.instance.parse(l));
                    case "if" -> statement = new StatementItem(IfStmntType.instance.parse(l));
                    case "function" -> statement = new StatementItem(VarDefType.instance.parse(l));
                    default -> throw new ParseTimeException("Illegal keyword: " + l.lookahead().getContent());
                }
            }
            case "bracket" -> {
                if ("{".equals(l.lookahead().getContent())) {
                    statement = new StatementItem(BodyType.instance.parse(l));
                } else {
                    throw new ParseTimeException("Illegal bracket: " + l.lookahead().getContent());
                }
            }
            case "variable" -> {
                statement = new StatementItem(VarDefType.instance.parse(l));
            }
            default -> throw new ParseTimeException("Illegal Type: " + l.lookahead().getType() + " at " + l.lookahead().getContent());
        }
        return statement;
    }
}
