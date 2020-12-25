package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.astNodes.FunctionAssignment;
import de.hskempten.tabulang.astNodes.PlaceholderNodes.ProceduralFTermNodeTest;
import de.hskempten.tabulang.items.ast.nodes.ProceduralFBodyAST;
import de.hskempten.tabulang.items.ast.nodes.ProceduralFTermAST;

import java.util.ArrayList;
import java.util.Collections;

public class ASTStatementSorter {

    public static ArrayList<StatementNode> sortStatements(ArrayList<StatementNode> statements) {

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

    private static boolean containsFunCallToNext(StatementNode actual, StatementNode next) {
        String nextName = "";
        if (ProceduralFTermAST.class.equals(next.getClass())) {
            nextName = ((ProceduralFTermNodeTest) next).getIdentifier().getIdentifier();
        } else if (ProceduralFBodyAST.class.equals(next.getClass())) {
            nextName = ((FunctionAssignment) next).getIdentifier().getIdentifier();
        } else return false;


        {
            if (ProceduralFTermAST.class.equals(actual.getClass())) {
                return getFunCallMethodNames(((ProceduralFTermNodeTest) actual).getTerm()).contains(nextName);
            } else if (ProceduralFBodyAST.class.equals(actual.getClass())) {
                for (int i = 0; i < ((FunctionAssignment) actual).getStatements().size(); i++) {
                    if (getFunCallMethodNames(((FunctionAssignment) actual).getStatements().get(i)).contains(nextName))
                        return true;
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isProceduralF(StatementNode statement) {
        return ProceduralFTermAST.class.equals(statement.getClass()) || ProceduralFBodyAST.class.equals(statement.getClass());
    }

    private static ArrayList<String> getFunCallMethodNames(Node statement) {
        if (FunctionCallNode.class.equals(statement.getClass())) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(((FunctionCallNode) statement).getNode().getIdentifier());
            return list;
        }
        return new ArrayList<String>();
    }
}
