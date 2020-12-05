package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class ASTStatementParser {

    ASTStatementParser.ShuntingYardBuilder syBuilder = new ASTStatementParser.ShuntingYardBuilder();

    public StatementAST parse(StatementAnyItem originalStatement) throws Exception {

        traverseStatement(originalStatement);
        StatementAST parsedStatement = statementParser(syBuilder.getOutput());

        return parsedStatement;
    }

    private void traverseStatement(StatementAnyItem originalStatement) {
        StatementAnyItem actStatement = originalStatement;
        int i = 20;
        while (i > 0) {
            switch (actStatement.getLanguageItemType()) {
                case STATEMENT_VARDEF -> {
                    switch (((StatementItem) actStatement).getMyVarDef().getLanguageItemType()) {
                        case VARDEF_ASSIGNMENT -> {
                            syBuilder.add(((StatementItem) actStatement).getMyVarDef());
                        }
                        case VARDEF_PROCEDURALF -> {
                            syBuilder.add(((StatementItem) actStatement).getMyVarDef().getMyProceduralF());
                        }
                    }
                }
                case ANYSTATEMENT_RETURN -> {
                    syBuilder.add(actStatement);
                }
                case STATEMENT_LOOP -> {
                    syBuilder.add(((StatementItem) actStatement).getMyLoop());
                }
                case LOOP_STMT_SET -> {
                    syBuilder.add(((LoopStmntItem) actStatement).getMySetStmnt());
                }
                default -> throw new IllegalStateException("Unexpected value: " + actStatement.getLanguageItemType());
            }
            return;
        }
    }

    private StatementAST statementParser(ArrayList<LanguageItem> items) throws Exception {


        LanguageItem actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);

        switch (actItem.getLanguageItemType()) {
            case VARDEF_ASSIGNMENT -> {
                String identifier = ((VarDefItem) actItem).getMyIdentifier().getMyString();
                TermAST term = new ASTTermParser().parse(((VarDefItem) actItem).getMyTerm());
                return new AssignmentAST(identifier, term);
            }
            case PROCEDURALF_FUNCBODY, PROCEDURALF_TERM -> {
                String identifier = ((ProceduralFItem) actItem).getMyIdentifier().getMyString();
                VListItem vList = ((ProceduralFItem) actItem).getMyVList();
                ArrayList<IdentifierAST> identifierList = new ArrayList<IdentifierAST>();
                if (!LanguageItemType.VLIST_EMPTY.equals(vList.getLanguageItemType())) {
                    identifierList.add(new IdentifierAST(vList.getMyIdentifier().getMyString()));
                    if (LanguageItemType.VLIST_MULTI.equals(vList.getLanguageItemType())) {
                        for (int i = 0; i < vList.getMyOtherIdentifiers().size(); i++) {
                            identifierList.add(new IdentifierAST(vList.getMyOtherIdentifiers().get(i).getMyString()));
                        }
                    }
                }

                switch (actItem.getLanguageItemType()) {
                    case PROCEDURALF_TERM -> {
                        TermAST term = new ASTTermParser().parse(((ProceduralFItem) actItem).getMyTerm());
                        return new ProceduralFTermAST(identifier, identifierList, term);
                    }
                    case PROCEDURALF_FUNCBODY -> {
                        FuncBodyItem bodyStatements = ((ProceduralFItem) actItem).getMyFuncBody();
                        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
                        // TODO refactor funcBodyItem
                        switch (bodyStatements.getLanguageItemType()) {
                            case FUNCBODY_RETURN -> {
                                statements.add(new ASTStatementParser().parse(bodyStatements.getMyReturnStmnt()));
                                return new ProceduralFBodyAST(identifier, identifierList, statements);
                            }
                            case FUNCBODY_RETURNS -> {
                                ArrayList<ReturnStmntItem> returnStatements = bodyStatements.getMyReturnStmnts();
                                for (int i = 0; i < returnStatements.size(); i++) {
                                    statements.add(new ASTStatementParser().parse(returnStatements.get(i)));
                                }
                                return new ProceduralFBodyAST(identifier, identifierList, statements);
                            }
                            case FUNCBODY_STATEMENTS -> {
                                LinkedList<StatementItem> myStatements = bodyStatements.getMyStatements();
                                for (int i = 0; i < myStatements.size(); i++) {
                                    statements.add(new ASTStatementParser().parse(myStatements.get(i)));
                                }
                                return new ProceduralFBodyAST(identifier, identifierList, statements);
                            }
                            default -> throw new IllegalStateException("Unexpected value: " + bodyStatements.getLanguageItemType());
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case ANYSTATEMENT_RETURN -> {
                TermAST term = new ASTTermParser().parse(((ReturnStmntItem) actItem).getMyTerm());
                return new StatementReturnAST(term);
            }
            case LOOP_LOOPBODY, LOOP_STATEMENT -> {
                LoopItem loopItem = (LoopItem) actItem;
                String identifier = loopItem.getMyIdentifier().getMyString();
                TermAST term = new ASTTermParser().parse(loopItem.getMyTerm());
                ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
                switch (actItem.getLanguageItemType()) {
                    case LOOP_STATEMENT -> {
                        statements.add(new ASTStatementParser().parse(loopItem.getMyLoopStmnt()));
                    }
                    case LOOP_LOOPBODY -> {
                        for (int i = 0; i < loopItem.getMyLoopBody().getMyLoopStmnts().size(); i++) {
                            statements.add(new ASTStatementParser().parse(loopItem.getMyLoopBody().getMyLoopStmnts().get(i)));
                        }
                    }
                }
                return new LoopAST(identifier, term, statements);
            }
            case ANYSTATEMENT_SET -> {
                TermAST term = new ASTTermParser().parse(((SetStmntItem)actItem).getMyTerm());
                return new StatementSetAST(term);
            }
            default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
        }
    }

    private static class ShuntingYardBuilder {
        Stack<LanguageItem> stack;
        ArrayList<LanguageItem> output;

        public ShuntingYardBuilder() {
            this.stack = new Stack<LanguageItem>();
            this.output = new ArrayList<LanguageItem>();
        }

        public void add(LanguageItem item) {
            switch (item.getLanguageItemType()) {
                case STATEMENT_IDENTIFIER, ORDINAL_NUMBER, VARDEF_ASSIGNMENT,
                        PROCEDURALF_FUNCBODY, PROCEDURALF_TERM, ANYSTATEMENT_RETURN,
                        LOOP_LOOPBODY, LOOP_STATEMENT, ANYSTATEMENT_SET -> {
                    output.add(item);
                }
                /*case TERM_BRACKET -> {
                    stack.push(item);
                }
                case TERMR_BRACKET -> {
                    while (!stack.isEmpty()
                            && !stack.peek().getLanguageItemType().equals(LanguageItemType.TERM_BRACKET)) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                }
                case OPERATOR_ADD -> {
                    while (!stack.isEmpty()
                            && isHigherPrecedence(item.getLanguageItemType())) {
                        output.add(stack.pop());
                    }
                    stack.push(item);
                }*/
                default -> throw new IllegalStateException("Unexpected value: " + item.getLanguageItemType());
            }
        }

        public boolean isHigherPrecedence(LanguageItemType type) {
            return LanguageItemType.isLeftAssociative(type)
                    && LanguageItemType.getPrecedence(type)
                    <= LanguageItemType.getPrecedence(stack.peek().getLanguageItemType())
                    || LanguageItemType.isRightAssociative(type)
                    && LanguageItemType.getPrecedence(type)
                    < LanguageItemType.getPrecedence(stack.peek().getLanguageItemType());
        }

        public ArrayList<LanguageItem> getOutput() throws Exception {
            while (!stack.isEmpty()) {
                if (LanguageItemType.TERM_BRACKET.equals(stack.peek().getLanguageItemType())) {
                    throw new Exception("Shunting Yard Builder Term invalid");
                }
                output.add(stack.pop());
            }
            return output;
        }

    }
}
