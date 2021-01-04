package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.FunctionAssignment;
import de.hskempten.tabulang.astNodes.FunctionCallNode;
import de.hskempten.tabulang.astNodes.GroupNode;
import de.hskempten.tabulang.astNodes.Node;

import java.util.ArrayList;
import java.util.Collections;

public class ASTStatementSorter {

    public static ArrayList<Node> sortStatements(ArrayList<Node> statements) {

        for (int n = statements.size() - 1; n > 0; n--) {
            for (int i = 0; i < n; i++) {
                if (!isProceduralF(statements.get(i)) && isProceduralF(statements.get(i + 1))) {
                    Collections.swap(statements, i, i + 1);
                } else if (isProceduralF(statements.get(i)) && isProceduralF(statements.get(i + 1)) && containsFunCallToNext(statements.get(i), statements.get(i + 1))) {
                    Collections.swap(statements, i, i + 1);
                } else if (isGroupNode(statements.get(i))) {
                    if (isGroupNode(statements.get(i + 1))) {
                        System.out.println("Here should be thrown an exception, because there a two GroupNodes in one StatementList");
                    } else {
                        Collections.swap(statements, i, i + 1);
                    }
                }
            }
        }
        return statements;
    }

    private static boolean isGroupNode(Node statementNode) {
        return statementNode instanceof GroupNode;
    }

    private static boolean containsFunCallToNext(Node actual, Node next) {
        String nextName = "";
        if (FunctionAssignment.class.equals(next.getClass())) {
            nextName = ((FunctionAssignment) next).getIdentifier().getIdentifier();
        } else return false;


        {
            if (FunctionAssignment.class.equals(actual.getClass())) {
                for (int i = 0; i < ((FunctionAssignment) actual).getStatements().size(); i++) {
                    if (getFunCallMethodNames(((FunctionAssignment) actual).getStatements().get(i)).contains(nextName))
                        return true;
                }
                return false;
            }
        }
        return false;
    }

    private static boolean isProceduralF(Node statement) {
        return FunctionAssignment.class.equals(statement.getClass());
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
