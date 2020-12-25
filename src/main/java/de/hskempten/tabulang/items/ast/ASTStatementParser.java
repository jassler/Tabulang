package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.astNodes.PlaceholderNodes.GroupFunctionCallNodeTest;
import de.hskempten.tabulang.astNodes.PlaceholderNodes.GroupNodeTest;
import de.hskempten.tabulang.astNodes.LoopNode;
import de.hskempten.tabulang.astNodes.PlaceholderNodes.ProceduralFBodyNodeTest;
import de.hskempten.tabulang.items.*;

import java.util.ArrayList;
import java.util.LinkedList;


public class ASTStatementParser {

    private int nestingLevel = 0;

    public StatementNode parse(StatementAnyItem originalStatement, int nestingLevel) throws Exception {
        this.nestingLevel = nestingLevel;
        return parse(originalStatement);
    }

    public StatementNode parse(StatementAnyItem originalStatement) throws Exception {

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

    private StatementNode statementParser(LanguageItem actItem) throws Exception {

        switch (actItem.getLanguageItemType()) {
            case VARDEF_ASSIGNMENT -> {
                IdentifierNode identifier = new IdentifierNode(((VarDefItem) actItem).getMyIdentifier().getMyString());
                TermNode term = new ASTTermParser().parse(((VarDefItem) actItem).getMyTerm());
                boolean isNewAssignment = ((VarDefItem) actItem).isNewAssignment();
                if (isNewAssignment) {
                    return new NewAssignmentNode(identifier, term);
                } else {
                    return new AssignmentNode(identifier, term);
                }
            }
            case PROCEDURALF_FUNCBODY, PROCEDURALF_TERM -> {
                IdentifierNode identifier = new IdentifierNode(((ProceduralFItem) actItem).getMyIdentifier().getMyString());
                VListItem vList = ((ProceduralFItem) actItem).getMyVList();
                ArrayList<IdentifierNode> identifierList = new ArrayList<IdentifierNode>();
                if (!LanguageItemType.VLIST_EMPTY.equals(vList.getLanguageItemType())) {
                    identifierList.add(new IdentifierNode(vList.getMyIdentifier().getMyString()));
                    if (LanguageItemType.VLIST_MULTI.equals(vList.getLanguageItemType())) {
                        for (int i = 0; i < vList.getMyOtherIdentifiers().size(); i++) {
                            identifierList.add(new IdentifierNode(vList.getMyOtherIdentifiers().get(i).getMyString()));
                        }
                    }
                }
                ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
                switch (actItem.getLanguageItemType()) {
                    case PROCEDURALF_TERM -> {
                        statements.add(new ASTStatementParser().parse(new ReturnStmntItem(((ProceduralFItem) actItem).getMyTerm())));
                    }
                    case PROCEDURALF_FUNCBODY -> {
                        FuncBodyItem bodyStatements = ((ProceduralFItem) actItem).getMyFuncBody();
                        LinkedList<StatementAnyItem> myStatements = bodyStatements.getMyStatements();
                        for (StatementAnyItem myStatement : myStatements) {
                            statements.add(new ASTStatementParser().parse(myStatement));
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                return new ProceduralFBodyNodeTest(identifier, identifierList, statements);
            }
            case ANYSTATEMENT_RETURN -> {
                TermNode term = new ASTTermParser().parse(((ReturnStmntItem) actItem).getMyTerm());
                return new ReturnNode(term);
            }
            case LOOP_LOOPBODY, LOOP_STATEMENT -> {
                LoopItem loopItem = (LoopItem) actItem;
                IdentifierNode identifier = new IdentifierNode(loopItem.getMyIdentifier().getMyString());
                TermNode term = new ASTTermParser().parse(loopItem.getMyTerm());
                ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
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
                return new LoopNode(identifier, term, statements, nestingLevel + 1);
            }
            case ANYSTATEMENT_SET -> {
                TermNode term = new ASTTermParser().parse(((SetStmntItem) actItem).getMyTerm());
                return new SetNode(term, nestingLevel);
            }
            case IF_WITHELSE, IF_WITHOUTELSE -> {
                IfStmntItem ifStmnt = (IfStmntItem) actItem;
                PredicateNode pred = new ASTPredParser().parse(ifStmnt.getMyPred());
                StatementNode ifStatement = new ASTStatementParser().parse(ifStmnt.getMyAnyStatement());

                switch (actItem.getLanguageItemType()) {
                    case IF_WITHOUTELSE -> {
                        return new IfNode(pred, ifStatement);
                    }
                    case IF_WITHELSE -> {
                        StatementNode elseStatement = new ASTStatementParser().parse(ifStmnt.getMyOptionalAnyStatement());
                        return new IfElseNode(pred, ifStatement, elseStatement);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case GROUP_EMPTY, GROUP_AREA, GROUP_HIDING_AREA, GROUP_HIDING -> {
                GroupStmntItem grp = (GroupStmntItem) actItem;
                TermNode term = new ASTTermParser().parse(grp.getMyTerm());
                return switch (actItem.getLanguageItemType()) {
                    case GROUP_EMPTY -> new GroupNodeTest(false, false, term);
                    case GROUP_AREA -> new GroupNodeTest(false, true, term);
                    case GROUP_HIDING_AREA -> new GroupNodeTest(true, true, term);
                    case GROUP_HIDING -> new GroupNodeTest(true, false, term);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                };
            }
            case GROUP_FUNCALL, GROUP_AREA_FUNCALL, GROUP_HIDING_AREA_FUNCALL, GROUP_HIDING_FUNCALL -> {
                GroupStmntItem grp = (GroupStmntItem) actItem;
                TermNode term = new ASTTermParser().parse(grp.getMyTerm());
                IdentifierNode fcIdentifier = new IdentifierNode(grp.getMyFunCall().getMyIdentifier().getMyString());
                ArrayList<TermNode> fcTerms = new ArrayList<TermNode>();
                for (int i = 0; i < grp.getMyFunCall().getTerms().size(); i++) {
                    fcTerms.add(new ASTTermParser().parse(grp.getMyFunCall().getTerms().get(i)));
                }
                FunctionCallNode funCall = new FunctionCallNode(fcIdentifier, fcTerms);
                return switch (actItem.getLanguageItemType()) {
                    case GROUP_FUNCALL -> new GroupFunctionCallNodeTest(false, false, term, funCall);
                    case GROUP_AREA_FUNCALL -> new GroupFunctionCallNodeTest(false, true, term, funCall);
                    case GROUP_HIDING_AREA_FUNCALL -> new GroupFunctionCallNodeTest(true, true, term, funCall);
                    case GROUP_HIDING_FUNCALL -> new GroupFunctionCallNodeTest(true, false, term, funCall);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                };
            }
            case MARK_WITHIF, MARK_WITHOUTIF -> {
                MarkStmntItem mrk = (MarkStmntItem) actItem;
                TermNode markTerm = new ASTTermParser().parse(mrk.getMyTerm());
                TermNode asTerm = new ASTTermParser().parse(mrk.getMySecondTerm());
                switch (actItem.getLanguageItemType()) {
                    case MARK_WITHOUTIF -> {
                        //TODO remove placeholder
                        return new IfNode(markTerm, asTerm);//Placeholder
                        //return new MarkNode(markTerm, asTerm);
                    }
                    case MARK_WITHIF -> {
                        PredicateNode ifPred = new ASTPredParser().parse(mrk.getMyPred());
                        //TODO remove placeholder
                        return new IfNode(markTerm, asTerm);//Placeholder
                        //return new MarkIfNode(markTerm, asTerm, ifPred);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
        }
    }
}
