package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.IdentifierItem;
import de.hskempten.tabulang.items.ProceduralFItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.items.VarDefItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class VarDefType implements Parser {

    public static VarDefType instance = new VarDefType();

    @Override
    public VarDefItem parse(Lexer l) throws ParseTimeException {

        VarDefItem item;

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                if ("function".equals(l.lookahead().getContent())) {
                    ProceduralFItem myProceduralF = ProceduralFType.instance.parse(l);
                    item = new VarDefItem(myProceduralF);
                } else {
                    throw new ParseTimeException("Illegal keyword: " + l.lookahead().getContent());
                }
            }
            case "variable" -> {
                IdentifierItem myIdentifier = IdentifierType.instance.parse(l);
                l.getNextTokenAndExpect(TokenType.ASSIGN);
                TermItem myTerm = TermType.instance.parse(l);
                l.getNextTokenAndExpect(TokenType.SEMICOLON);
                item = new VarDefItem(myIdentifier, myTerm);
            }
            default -> throw new ParseTimeException("Illegal type: " + l.lookahead().getType() + " at " + l.lookahead().getContent());
        }
        return item;
    }
}
