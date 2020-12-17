package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.items.ast.interfaces.AST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;

import java.util.ArrayList;

public class ProgramAST implements AST {
    private ArrayList<StatementAST> statements;

    public ProgramAST(ArrayList<StatementAST> statements) {
        this.setStatements(statements);
    }

    public ArrayList<StatementAST> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementAST> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    @Override
    public void print(int offset) {
        System.out.println();
        System.out.println("------------");
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        for (int i = 0; i < this.getStatements().size(); i++) {
            this.getStatements().get(i).print(offset + this.getClass().getSimpleName().length() + 1);
        }

    }
}
