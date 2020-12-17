package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.ast.interfaces.AST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.nodes.FunCallAST;
import de.hskempten.tabulang.items.ast.nodes.ProceduralFBodyAST;
import de.hskempten.tabulang.items.ast.nodes.ProceduralFTermAST;

import java.util.ArrayList;
import java.util.Collections;

public class ASTStatementSorter {

    public static ArrayList<StatementAST> sortStatements(ArrayList<StatementAST> statements) {

        for (int n = statements.size() - 1; n > 0; n--) {
            for (int i = 0; i < n; i++) {
                if (!isProceduralF(statements.get(i)) && isProceduralF(statements.get(i + 1))) {
                    Collections.swap(statements, i, i + 1);
                } else if (isProceduralF(statements.get(i)) && isProceduralF(statements.get(i + 1)) && containsFunCallToNext(statements.get(i), statements.get(i + 1))) {
                    Collections.swap(statements, i, i + 1);
                }
            }
        }
        return statements;
    }

    private static boolean containsFunCallToNext(StatementAST actual, StatementAST next) {
        String nextName = "";
        if (ProceduralFTermAST.class.equals(next.getClass())) {
            nextName = ((ProceduralFTermAST) next).getIdentifier().getString();
        } else if (ProceduralFBodyAST.class.equals(next.getClass())) {
            nextName = ((ProceduralFBodyAST) next).getIdentifier().getString();
        } else return false;


        {
            if (ProceduralFTermAST.class.equals(actual.getClass())) {
                return getFunCallMethodNames(((ProceduralFTermAST) actual).getTerm()).contains(nextName);
            } else if (ProceduralFBodyAST.class.equals(actual.getClass())) {
                for (int i = 0; i < ((ProceduralFBodyAST) actual).getStatements().size(); i++) {
                    if (getFunCallMethodNames(((ProceduralFBodyAST) actual).getStatements().get(i)).contains(nextName))
                        return true;
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isProceduralF(StatementAST statement) {
        return ProceduralFTermAST.class.equals(statement.getClass()) || ProceduralFBodyAST.class.equals(statement.getClass());
    }

    private static ArrayList<String> getFunCallMethodNames(AST statement) {
        if (FunCallAST.class.equals(statement.getClass())) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(((FunCallAST) statement).getIdentifier().getString());
            return list;
        }
        return new ArrayList<String>();
    }
}
