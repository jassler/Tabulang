package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.StatementItem;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.AssignmentAST;

public class ASTStatementParser {

    public static ASTStatementParser instance = new ASTStatementParser();

    public StatementAST parse(StatementItem item) throws Exception {
        switch (item.getLanguageItemType()) {
            case STATEMENT_VARDEF -> {
                switch (item.getMyVarDef().getLanguageItemType()) {
                    case VARDEF_ASSIGNMENT -> {
                        String identifier = item.getMyVarDef().getMyIdentifier().getMyString();
                        TermAST term = ASTTermParser.instance.parse(item.getMyVarDef().getMyTerm());
                        return new AssignmentAST(identifier, term);
                    }
                }
            }
        }
        return null;
    }
}
