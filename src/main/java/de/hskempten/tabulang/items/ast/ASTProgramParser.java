package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.nodes.ProgramAST;

import java.util.ArrayList;

public class ASTProgramParser {

    public static ASTProgramParser instance = new ASTProgramParser();

    /**
     * Converts the list of statements created from the programme code based on the language syntax into an abstract syntax tree
     *
     * @param prg Contains a list of statements created when parsing the programme code
     * @return The Abstract Syntax Tree for the program
     * @throws Exception
     */
    public ProgramAST parse(ProgramItem prg) throws Exception {
        ArrayList<Node> statements = new ArrayList<Node>();
        for (int i = 0; i < prg.getMyStatements().size(); i++) {
            StatementNode statement = new ASTStatementParser().parse(prg.getMyStatements().get(i));
            statements.add(statement);
        }
        return new ProgramAST(statements);
    }
}
