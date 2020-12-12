package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;
import java.util.LinkedList;


public class ASTStatementParser {

    private int nestingLevel = 0;

    public StatementAST parse(StatementAnyItem originalStatement, int nestingLevel) throws Exception {
        this.nestingLevel = nestingLevel;
        return parse(originalStatement);
    }

    public StatementAST parse(StatementAnyItem originalStatement) throws Exception {

        return statementParser(traverseStatement(originalStatement));
    }

    private LanguageItem traverseStatement(StatementAnyItem originalStatement) throws Exception {
        StatementAnyItem actStatement = originalStatement;


        switch (actStatement.getLanguageItemType()) {
            case STATEMENT_VARDEF -> {
                return switch (((StatementItem) actStatement).getMyVarDef().getLanguageItemType()) {
                    case VARDEF_ASSIGNMENT -> ((StatementItem) actStatement).getMyVarDef();
                    case VARDEF_PROCEDURALF -> ((StatementItem) actStatement).getMyVarDef().getMyProceduralF();
                    default -> throw new IllegalStateException("Unexpected value: " + ((StatementItem) actStatement).getMyVarDef().getLanguageItemType());
                };
            }
            case ANYSTATEMENT_STATEMENT -> {
                StatementItem statement = ((AnyStatementItem) actStatement).getMyStatement();
                return switch (statement.getLanguageItemType()) {
                    case STATEMENT_LOOP -> statement.getMyLoop();
                    case STATEMENT_IF -> statement.getMyIfStmnt();
                    case STATEMENT_BODY -> statement.getMyBody();
                    case STATEMENT_VARDEF -> statement.getMyVarDef();
                    default -> throw new IllegalStateException("Unexpected value: " + statement.getLanguageItemType());
                };
            }
            case ANYSTATEMENT_RETURN -> {
                return actStatement;
            }
            case ANYSTATEMENT_SET -> {
                return ((AnyStatementItem) actStatement).getMySetStmnt();
            }
            case ANYSTATEMENT_GROUP -> {
                return ((AnyStatementItem) actStatement).getMyGroupStmnt();
            }
            case STATEMENT_LOOP -> {
                return ((StatementItem) actStatement).getMyLoop();
            }
            case LOOP_STMT_SET -> {
                return ((LoopStmntItem) actStatement).getMySetStmnt();
            }
            case LOOP_STMT_GROUP -> {
                return ((LoopStmntItem) actStatement).getMyGroupStmnt();
            }
            case LOOP_STMT_MARK -> {
                return ((LoopStmntItem) actStatement).getMyMarkStmnt();
            }
            case LOOP_STMT_STATEMENT -> {
                StatementItem statement = ((LoopStmntItem) actStatement).getMyStatement();
                return switch (statement.getLanguageItemType()) {
                    case STATEMENT_LOOP -> statement.getMyLoop();
                    case STATEMENT_IF -> statement.getMyIfStmnt();
                    case STATEMENT_VARDEF -> statement.getMyVarDef();
                    case STATEMENT_BODY -> statement.getMyBody();
                    default -> throw new IllegalStateException("Unexpected value: " + statement.getLanguageItemType());
                };
            }
            case STATEMENT_IF -> {
                return ((StatementItem) actStatement).getMyIfStmnt();
            }
            // TODO implement case STATEMENT_BODY
            default -> throw new IllegalStateException("Unexpected value: " +
                    actStatement.getLanguageItemType() + " " + actStatement.getClass().getSimpleName());
        }
    }

    private StatementAST statementParser(LanguageItem actItem) throws Exception {

        switch (actItem.getLanguageItemType()) {
            case VARDEF_ASSIGNMENT -> {
                IdentifierAST identifier = new IdentifierAST(((VarDefItem) actItem).getMyIdentifier().getMyString());
                TermAST term = new ASTTermParser().parse(((VarDefItem) actItem).getMyTerm());
                boolean isNewAssignment = ((VarDefItem) actItem).isNewAssignment();
                return new AssignmentAST(identifier, term, isNewAssignment);
            }
            case PROCEDURALF_FUNCBODY, PROCEDURALF_TERM -> {
                IdentifierAST identifier = new IdentifierAST(((ProceduralFItem) actItem).getMyIdentifier().getMyString());
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
                IdentifierAST identifier = new IdentifierAST(loopItem.getMyIdentifier().getMyString());
                TermAST term = new ASTTermParser().parse(loopItem.getMyTerm());
                ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
                switch (actItem.getLanguageItemType()) {
                    case LOOP_STATEMENT -> {
                        statements.add(new ASTStatementParser().parse(loopItem.getMyLoopStmnt(), nestingLevel + 1));
                    }
                    case LOOP_LOOPBODY -> {
                        for (int i = 0; i < loopItem.getMyLoopBody().getMyLoopStmnts().size(); i++) {
                            statements.add(new ASTStatementParser().parse(loopItem.getMyLoopBody().getMyLoopStmnts().get(i), nestingLevel + 1));
                        }
                    }
                }
                return new LoopAST(identifier, term, statements, nestingLevel + 1);
            }
            case ANYSTATEMENT_SET -> {
                TermAST term = new ASTTermParser().parse(((SetStmntItem) actItem).getMyTerm());
                return new StatementSetAST(term, nestingLevel);
            }
            case IF_WITHELSE, IF_WITHOUTELSE -> {
                IfStmntItem ifStmnt = (IfStmntItem) actItem;
                PredAST pred = new ASTPredParser().parse(ifStmnt.getMyPred());
                StatementAST ifStatement = new ASTStatementParser().parse(ifStmnt.getMyAnyStatement());

                switch (actItem.getLanguageItemType()) {
                    case IF_WITHOUTELSE -> {
                        return new StatementIfAST(pred, ifStatement);
                    }
                    case IF_WITHELSE -> {
                        StatementAST elseStatement = new ASTStatementParser().parse(ifStmnt.getMyOptionalAnyStatement());
                        return new StatementIfElseAST(pred, ifStatement, elseStatement);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case GROUP_EMPTY, GROUP_AREA, GROUP_HIDING_AREA, GROUP_HIDING -> {
                GroupStmntItem grp = (GroupStmntItem) actItem;
                TermAST term = new ASTTermParser().parse(grp.getMyTerm());
                return switch (actItem.getLanguageItemType()) {
                    case GROUP_EMPTY -> new GroupAST(false, false, term);
                    case GROUP_AREA -> new GroupAST(false, true, term);
                    case GROUP_HIDING_AREA -> new GroupAST(true, true, term);
                    case GROUP_HIDING -> new GroupAST(true, false, term);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                };
            }
            case GROUP_FUNCALL, GROUP_AREA_FUNCALL, GROUP_HIDING_AREA_FUNCALL, GROUP_HIDING_FUNCALL -> {
                GroupStmntItem grp = (GroupStmntItem) actItem;
                TermAST term = new ASTTermParser().parse(grp.getMyTerm());
                IdentifierAST fcIdentifier = new IdentifierAST(grp.getMyFunCall().getMyIdentifier().getMyString());
                ArrayList<TermAST> fcTerms = new ArrayList<TermAST>();
                for (int i = 0; i < grp.getMyFunCall().getTerms().size(); i++) {
                    fcTerms.add(new ASTTermParser().parse(grp.getMyFunCall().getTerms().get(i)));
                }
                FunCallAST funCall = new FunCallAST(fcIdentifier, fcTerms);
                return switch (actItem.getLanguageItemType()) {
                    case GROUP_FUNCALL -> new GroupFunCallAST(false, false, term, funCall);
                    case GROUP_AREA_FUNCALL -> new GroupFunCallAST(false, true, term, funCall);
                    case GROUP_HIDING_AREA_FUNCALL -> new GroupFunCallAST(true, true, term, funCall);
                    case GROUP_HIDING_FUNCALL -> new GroupFunCallAST(true, false, term, funCall);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                };
            }
            case MARK_WITHIF, MARK_WITHOUTIF -> {
                MarkStmntItem mrk = (MarkStmntItem) actItem;
                TermAST markTerm = new ASTTermParser().parse(mrk.getMyTerm());
                TermAST asTerm = new ASTTermParser().parse(mrk.getMySecondTerm());
                switch (actItem.getLanguageItemType()) {
                    case MARK_WITHOUTIF -> {
                        return new MarkAST(markTerm, asTerm);
                    }
                    case MARK_WITHIF -> {
                        PredAST ifPred = new ASTPredParser().parse(mrk.getMyPred());
                        return new MarkIfAST(markTerm, asTerm, ifPred);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
        }
    }
}
