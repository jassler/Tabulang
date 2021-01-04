package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.LinkedList;


public class ASTStatementParser {

    private int nestingLevel = 0;

    public Node parse(StatementAnyItem originalStatement, int nestingLevel) throws Exception {
        this.nestingLevel = nestingLevel;
        return parse(originalStatement);
    }

    public Node parse(StatementAnyItem originalStatement) throws Exception {

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
                ((LoopStmntItem) actStatement).getMyMarkStmnt().setLanguageItemType(switch (((LoopStmntItem) actStatement).getMyMarkStmnt().getLanguageItemType()) {
                    case MARK_WITHOUTIF -> LanguageItemType.LOOP_MARK_WITHOUTIF;
                    case MARK_WITHIF -> LanguageItemType.LOOP_MARK_WITHIF;
                    default -> throw new IllegalStateException("Unexpected value: " + ((LoopStmntItem) actStatement).getMyMarkStmnt().getLanguageItemType());
                });
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
            case STATEMENT_FUNCALL -> {
                return ((StatementItem) actStatement).getMyFunCall();
            }
            // TODO implement case STATEMENT_BODY
            default -> throw new IllegalStateException("Unexpected value: " +
                    actStatement.getLanguageItemType() + " " + actStatement.getClass().getSimpleName());
        }
    }

    private Node statementParser(LanguageItem actItem) throws Exception {
        TextPosition textPosition = actItem.getTextPosition();
        switch (actItem.getLanguageItemType()) {
            case VARDEF_ASSIGNMENT -> {
                IdentifierNode identifier = new IdentifierNode(((VarDefItem) actItem).getMyIdentifier().getMyString(), textPosition);
                TermNode term = new ASTTermParser().parse(((VarDefItem) actItem).getMyTerm());
                boolean isNewAssignment = ((VarDefItem) actItem).isNewAssignment();
                if (isNewAssignment) {
                    return new NewAssignmentNode(identifier, term, textPosition);
                } else {
                    return new AssignmentNode(identifier, term, textPosition);
                }
            }
            case PROCEDURALF_FUNCBODY, PROCEDURALF_TERM -> {
                IdentifierNode identifier = new IdentifierNode(((ProceduralFItem) actItem).getMyIdentifier().getMyString(), textPosition);
                VListItem vList = ((ProceduralFItem) actItem).getMyVList();
                ArrayList<IdentifierNode> identifierList = new ArrayList<IdentifierNode>();
                if (!LanguageItemType.VLIST_EMPTY.equals(vList.getLanguageItemType())) {
                    identifierList.add(new IdentifierNode(vList.getMyIdentifier().getMyString(), textPosition));
                    if (LanguageItemType.VLIST_MULTI.equals(vList.getLanguageItemType())) {
                        for (int i = 0; i < vList.getMyOtherIdentifiers().size(); i++) {
                            identifierList.add(new IdentifierNode(vList.getMyOtherIdentifiers().get(i).getMyString(), textPosition));
                        }
                    }
                }
                ArrayList<Node> statements = new ArrayList<Node>();
                switch (actItem.getLanguageItemType()) {
                    case PROCEDURALF_TERM -> {
                        statements.add((StatementNode) new ASTStatementParser().parse(new ReturnStmntItem(((ProceduralFItem) actItem).getMyTerm())));
                    }
                    case PROCEDURALF_FUNCBODY -> {
                        FuncBodyItem bodyStatements = ((ProceduralFItem) actItem).getMyFuncBody();
                        LinkedList<StatementAnyItem> myStatements = bodyStatements.getMyStatements();
                        for (StatementAnyItem myStatement : myStatements) {
                            statements.add((StatementNode) new ASTStatementParser().parse(myStatement));
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                return new FunctionAssignment(identifier, identifierList, statements, textPosition);
            }
            case ANYSTATEMENT_RETURN -> {
                TermNode term = new ASTTermParser().parse(((ReturnStmntItem) actItem).getMyTerm());
                return new ReturnNode(term, textPosition);
            }
            case LOOP_LOOPBODY, LOOP_STATEMENT -> {
                LoopItem loopItem = (LoopItem) actItem;
                IdentifierNode identifier = new IdentifierNode(loopItem.getMyIdentifier().getMyString(), textPosition);
                TermNode term = new ASTTermParser().parse(loopItem.getMyTerm());
                ArrayList<Node> statements = new ArrayList<Node>();
                switch (actItem.getLanguageItemType()) {
                    case LOOP_STATEMENT -> {
                        statements.add((StatementNode) new ASTStatementParser().parse(loopItem.getMyLoopStmnt(), nestingLevel + 1));
                    }
                    case LOOP_LOOPBODY -> {
                        for (int i = 0; i < loopItem.getMyLoopBody().getMyLoopStmnts().size(); i++) {
                            statements.add((StatementNode) new ASTStatementParser().parse(loopItem.getMyLoopBody().getMyLoopStmnts().get(i), nestingLevel + 1));
                        }
                    }
                }
                return new LoopStatementNode(identifier, term, statements, nestingLevel + 1, textPosition);
            }
            case ANYSTATEMENT_SET -> {
                TermNode term = new ASTTermParser().parse(((SetStmntItem) actItem).getMyTerm());
                return new SetNode(term, nestingLevel, textPosition);
            }
            case IF_WITHELSE, IF_WITHOUTELSE -> {
                IfStmntItem ifStmnt = (IfStmntItem) actItem;
                PredicateNode pred = new ASTPredParser().parse(ifStmnt.getMyPred());
                StatementNode ifStatement = (StatementNode) new ASTStatementParser().parse(ifStmnt.getMyAnyStatement());

                switch (actItem.getLanguageItemType()) {
                    case IF_WITHOUTELSE -> {
                        return new IfNode(pred, ifStatement, textPosition);
                    }
                    case IF_WITHELSE -> {
                        StatementNode elseStatement = (StatementNode) new ASTStatementParser().parse(ifStmnt.getMyOptionalAnyStatement());
                        return new IfElseNode(pred, ifStatement, elseStatement, textPosition);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case GROUP_EMPTY, GROUP_AREA, GROUP_HIDING_AREA, GROUP_HIDING -> {
                GroupStmntItem grp = (GroupStmntItem) actItem;
                TermNode term = new ASTTermParser().parse(grp.getMyTerm());
                return new GroupWithoutFunctionCallNode(term, textPosition);
            }
            case GROUP_FUNCALL, GROUP_AREA_FUNCALL, GROUP_HIDING_AREA_FUNCALL, GROUP_HIDING_FUNCALL -> {
                GroupStmntItem grp = (GroupStmntItem) actItem;
                TermNode term = new ASTTermParser().parse(grp.getMyTerm());
                IdentifierNode fcIdentifier = new IdentifierNode(grp.getMyFunCall().getMyIdentifier().getMyString(), textPosition);
                ArrayList<TermNode> fcTerms = new ArrayList<TermNode>();
                for (int i = 0; i < grp.getMyFunCall().getTerms().size(); i++) {
                    fcTerms.add(new ASTTermParser().parse(grp.getMyFunCall().getTerms().get(i)));
                }
                FunctionCallNode funCall = new FunctionCallNode(fcIdentifier, fcTerms, textPosition);
                return switch (actItem.getLanguageItemType()) {
                    case GROUP_FUNCALL -> new GroupBeforeFunctionCallNode(term, funCall, textPosition);
                    case GROUP_AREA_FUNCALL -> switch (grp.getMyGroupArea().getMyString()) {
                        case "before" -> new GroupBeforeFunctionCallNode(term, funCall, textPosition);
                        case "after" -> new GroupAfterFunctionCallNode(term, funCall, textPosition);
                        default -> throw new IllegalStateException("Unexpected area value: " + grp.getMyGroupArea().getMyString());
                    };
                    case GROUP_HIDING_AREA_FUNCALL, GROUP_HIDING_FUNCALL -> new HidingGroupFunctionCallNode(term, funCall, textPosition);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                };
            }
            case LOOP_MARK_WITHIF, LOOP_MARK_WITHOUTIF -> {
                MarkStmntItem mrk = (MarkStmntItem) actItem;
                TermNode markTerm = new ASTTermParser().parse(mrk.getMyTerm());
                TermNode asTerm = new ASTTermParser().parse(mrk.getMySecondTerm());
                switch (actItem.getLanguageItemType()) {
                    case LOOP_MARK_WITHOUTIF -> {
                        return new MarkInLoopNode(markTerm, asTerm, textPosition);
                    }
                    case LOOP_MARK_WITHIF -> {
                        PredicateNode ifPred = new ASTPredParser().parse(mrk.getMyPred());
                        return new MarkIfInLoopNode(markTerm, asTerm, ifPred, textPosition);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case TERM_FUNCALL -> {
                FunCallItem fc = (FunCallItem) actItem;
                IdentifierNode identifier = new IdentifierNode(fc.getMyIdentifier().getMyString(), textPosition);
                ArrayList<TermNode> terms = new ArrayList<>();
                for (int i = 0; i < fc.getTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(fc.getTerms().get(i)));
                }
                return new FunctionCallNode(identifier, terms, textPosition);
            }
            default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
        }
    }
}
