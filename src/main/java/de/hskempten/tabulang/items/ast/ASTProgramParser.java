package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;

import java.util.ArrayList;

public class ASTProgramParser {

    public static ASTProgramParser instance = new ASTProgramParser();

    public ProgramAST parse(ProgramItem prg) throws Exception {
        ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
        for (int i = 0; i < prg.getMyStatements().size(); i++) {
            StatementNode statement = new ASTStatementParser().parse(prg.getMyStatements().get(i));
            statements.add(statement);
        }
        return new ProgramAST(statements);
    }
}
