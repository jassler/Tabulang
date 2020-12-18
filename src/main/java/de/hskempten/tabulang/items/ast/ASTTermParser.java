package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.astNodes.PlaceholderNodes.LoopNode;
import de.hskempten.tabulang.items.*;

import java.util.ArrayList;
import java.util.Stack;

public class ASTTermParser {

    ShuntingYardBuilder syBuilder = new ShuntingYardBuilder();
    private int nestingLevel = 0;

    public TermNode parse(TermItem originalTerm, int nestingLevel) throws Exception {
        this.nestingLevel = nestingLevel;
        return parse(originalTerm);
    }

    public TermNode parse(TermItem originalTerm) throws Exception {

        traverseTerm(originalTerm);
        TermNode parsedTerm = termParser(syBuilder.getOutput());

        return parsedTerm;
    }

    private void traverseTerm(TermItem originalTerm) throws Exception {
        TermOrRItem actTerm = originalTerm;
        int i = 20;
        while (i > 0) {
            switch (actTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER -> syBuilder.add(((TermItem) actTerm).getMyIdentifier());
                case TERM_ORDINAL -> {
                    switch (((TermItem) actTerm).getMyOrdinal().getLanguageItemType()) {
                        case ORDINAL_NUMBER -> syBuilder.add(((TermItem) actTerm).getMyOrdinal().getMyNumber());
                        case ORDINAL_TUPEL -> syBuilder.add(((TermItem) actTerm).getMyOrdinal().getMyTupel());
                        case ORDINAL_QUOTEDSTRING -> syBuilder.add(((TermItem) actTerm).getMyOrdinal().getMyQuotedString());
                        case ORDINAL_NULL -> syBuilder.add(((TermItem) actTerm).getMyOrdinal());
                        default -> throw new IllegalStateException("Unexpected value: " + ((TermItem) actTerm).getMyOrdinal().getLanguageItemType());
                    }
                }
                case TERMR_OPERATOR -> syBuilder.add(((TermRItem) actTerm).getMyOperator());
                case TERM_BRACKET -> {
                    syBuilder.add(actTerm);
                    traverseTerm(((TermItem) actTerm).getMyTerm());
                }
                case TERMR_BRACKET -> {
                    syBuilder.add(actTerm);
                    return;
                }
                case TERMR_NULL -> {
                    return;
                }
                case TERM_DIRECTIONAL -> {
                    syBuilder.add(((TermItem) actTerm).getMyDirectionalTerm());
                }
                case TERM_FUNCALL -> {
                    syBuilder.add(((TermItem) actTerm).getMyFunCall());
                }
                case TERM_AGGREGATION -> {
                    syBuilder.add(((TermItem) actTerm).getMyAggregationT());
                }
                case TERM_DISTINCT -> {
                    syBuilder.add(((TermItem) actTerm).getMyDistinctT());
                }
                case TERM_LOOP -> {
                    syBuilder.add(((TermItem) actTerm).getMyLoop());
                }
                case TERMR_MARK -> {
                    syBuilder.add(((TermRItem) actTerm).getMyMarkStmnt());
                }
                case TERMR_FILTER -> {
                    syBuilder.add(actTerm);
                }
                case TERM_FUNDEF -> {
                    syBuilder.add(((TermItem) actTerm).getMyFunDef());
                }
                case TERMR_DOT -> {
                    syBuilder.add(actTerm);
                    traverseTerm(((TermRItem) actTerm).getMyTerm());
                }
                default -> {
                    throw new Exception("ASTTermParser case not yet implemented: " + actTerm.getLanguageItemType());
                }
            }
            actTerm = switch (actTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER, TERM_ORDINAL, TERM_DIRECTIONAL, TERM_FUNCALL, TERM_AGGREGATION,
                        TERM_DISTINCT, TERM_LOOP, TERMR_MARK, TERMR_FILTER, TERM_FUNDEF, TERMR_DOT -> actTerm.getMyTermR();
                case TERMR_OPERATOR -> ((TermRItem) actTerm).getMyTerm();
                case TERM_BRACKET -> actTerm.getMyTermR();
                default -> {
                    throw new IllegalStateException("Unexpected value: " + actTerm.getLanguageItemType());
                }
            };
            i--;
        }
    }

    private TermNode termParser(ArrayList<LanguageItem> items) throws Exception {


        LanguageItem actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);

        switch (actItem.getLanguageItemType()) {
            case OPERATOR_ADD -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new AddNode(left, right);
            }
            case OPERATOR_SUBTRACT -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new SubtractNode(left, right);
            }
            case OPERATOR_MULTIPLY -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new MultiplyNode(left, right);
            }
            case OPERATOR_MOD -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new ModNode(left, right);
            }
            case OPERATOR_DIV -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new DivNode(left, right);
            }
            case OPERATOR_DIVIDE -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new DivisionNode(left, right);
            }
            case OPERATOR_POWER -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new PowerNode(left, right);
            }
            case ORDINAL_NUMBER -> {
                NumberItem nItem = (NumberItem) actItem;
                NumberNode item = new NumberNode(nItem.getNumerator(), nItem.getDenominator());

                return item;
            }
            case STATEMENT_IDENTIFIER -> {
                IdentifierNode item = new IdentifierNode(((IdentifierItem) actItem).getMyString());
                return item;
            }
            case TUPEL_EMPTY -> {
                TupleNode item = new TupleNode();
                return item;
            }
            case TUPEL_ONE -> {
                TupelItem tupel = (TupelItem) actItem;
                TermNode term = new ASTTermParser().parse(tupel.getMyTerm());
                TupleNode item = new TupleNode(term);
                return item;
            }
            case TUPEL_MULTI -> {
                TupelItem tupel = (TupelItem) actItem;
                ArrayList<TermNode> terms = new ArrayList<TermNode>();
                terms.add(new ASTTermParser().parse(tupel.getMyTerm()));
                for (int i = 0; i < tupel.getMyTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(tupel.getMyTerms().get(i)));
                }
                TupleNode item = new TupleNode(terms);
                return item;
            }
            case TUPEL_INTERVAL -> {
                TupelItem tupel = (TupelItem) actItem;
                TermNode firstTerm = new ASTTermParser().parse(tupel.getMyIntervall().getMyTerm());
                TermNode secondTerm = new ASTTermParser().parse(tupel.getMyIntervall().getMySecondTerm());
                return new SpreadNode(firstTerm, secondTerm);
            }
            case ORDINAL_QUOTEDSTRING -> {
                String string = ((QuotedStringItem) actItem).getMyString();
                StringNode item = new StringNode(string);
                return item;
            }
            case ORDINAL_NULL -> {
                NullNode item = new NullNode();
                return item;
            }
            case TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V -> {
               TermNode item;
                TermNode term = new ASTTermParser().parse(((DirectionalTermItem) actItem).getMyTerm());
                switch (actItem.getLanguageItemType()) {
                    case TERM_DIRECTIONAL_H -> item = new HorizontalTupleNode(term);
                    case TERM_DIRECTIONAL_V -> item = new VerticalTupleNode(term);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                return item;
            }
            case TERM_FUNCALL -> {
                IdentifierNode identifier = new IdentifierNode(((FunCallItem) actItem).getMyIdentifier().getMyString());
                ArrayList<TermNode> terms = new ArrayList<TermNode>();
                for (int i = 0; i < ((FunCallItem) actItem).getTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(((FunCallItem) actItem).getTerms().get(i)));
                }
                return new FunctionCallNode(identifier, terms);
            }
            case AGGREGATION_AVERAGE -> {
                AverageTItem ave = ((AggregationTItem) actItem).getMyAverageT();
                IdentifierNode identifier = new IdentifierNode(ave.getMyIdentifier().getMyString());
                TermNode term = new ASTTermParser().parse(ave.getMyTerm());
                return new AverageNode(identifier, term);

            }
            case AGGREGATION_COUNT -> {
                CountTItem cnt = ((AggregationTItem) actItem).getMyCountT();
                TermNode term = new ASTTermParser().parse(cnt.getMyTerm());
                switch (cnt.getLanguageItemType()) {
                    case COUNT_EMPTY -> {
                        return new CountNode(term);
                    }
                    case COUNT_DIRECTIONAL -> {
                        switch (cnt.getMyString()) {
                            case "horizontal":
                                return new CountHorizontalNode(term);
                            case "vertical":
                                return new CountVerticalNode(term);
                            default:
                                throw new IllegalStateException("Unexpected value: " + cnt.getMyString());
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + cnt.getLanguageItemType());
                }
            }
            case DISTINCT_ITEM -> {
                DistinctTItem dis = (DistinctTItem) actItem;
                ArrayList<IdentifierNode> identifiers = new ArrayList<IdentifierNode>();
                for (int i = 0; i < dis.getMyIdentifiers().size(); i++) {
                    identifiers.add(new IdentifierNode(dis.getMyIdentifiers().get(i).getMyString()));
                }
                TermNode term = new ASTTermParser().parse(dis.getMyTerm());
                return new DistinctFromNode(term, identifiers);
            }
            case LOOP_LOOPBODY, LOOP_STATEMENT -> {
                LoopItem loop = (LoopItem) actItem;
                IdentifierNode identifier = new IdentifierNode(loop.getMyIdentifier().getMyString());
                TermNode term = new ASTTermParser().parse(loop.getMyTerm());
                ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
                switch (actItem.getLanguageItemType()) {
                    case LOOP_LOOPBODY -> {
                        for (int i = 0; i < loop.getMyLoopBody().getMyLoopStmnts().size(); i++) {
                            statements.add(new ASTStatementParser().parse(loop.getMyLoopBody().getMyLoopStmnts().get(i), nestingLevel + 1));
                        }
                    }
                    case LOOP_STATEMENT -> {
                        statements.add(new ASTStatementParser().parse(loop.getMyLoopStmnt(), nestingLevel + 1));
                    }
                }
                return new LoopNode(identifier, term, statements, nestingLevel + 1);
            }
            case MARK_WITHIF, MARK_WITHOUTIF -> {
                MarkStmntItem mark = (MarkStmntItem) actItem;
                TermNode markTerm = new ASTTermParser().parse(mark.getMyTerm());
                TermNode asTerm = new ASTTermParser().parse(mark.getMySecondTerm());
                switch (actItem.getLanguageItemType()) {
                    case MARK_WITHOUTIF -> {
                        return new MarkNode(markTerm, asTerm);
                    }
                    case MARK_WITHIF -> {
                        PredicateNode pred = new ASTPredParser().parse(mark.getMyPred());
                        return new MarkIfNode(markTerm, asTerm, pred);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case TERMR_FILTER -> {
                ArrayList<PredicateNode> preds = new ArrayList<PredicateNode>();
                //TODO remove once filter constructor is correct
                return new FilterNode(null, null);
                //return new FilterNode(preds);
            }
            case FUNDEF_IDENTIFIER_TERM, FUNDEF_IDENTIFIER_FUNCBODY, FUNDEF_VLIST_TERM, FUNDEF_VLIST_FUNCBODY -> {
                FunDefItem fundef = (FunDefItem) actItem;
                ArrayList<IdentifierNode> identifiers = new ArrayList<IdentifierNode>();
                switch (actItem.getLanguageItemType()) {
                    case FUNDEF_IDENTIFIER_TERM, FUNDEF_IDENTIFIER_FUNCBODY -> {
                        identifiers.add(new IdentifierNode(fundef.getMyIdentifier().getMyString()));
                    }
                    case FUNDEF_VLIST_TERM, FUNDEF_VLIST_FUNCBODY -> {
                        switch (fundef.getMyVList().getLanguageItemType()) {
                            case VLIST_ONE, VLIST_MULTI -> {
                                identifiers.add(new IdentifierNode(fundef.getMyVList().getMyIdentifier().getMyString()));
                                if (LanguageItemType.VLIST_MULTI.equals(fundef.getMyVList().getLanguageItemType())) {
                                    for (int i = 0; i < fundef.getMyVList().getMyOtherIdentifiers().size(); i++) {
                                        identifiers.add(new IdentifierNode(fundef.getMyVList().getMyOtherIdentifiers().get(i).getMyString()));
                                    }
                                }
                            }
                            default -> throw new IllegalStateException("Unexpected value: " + fundef.getMyVList().getLanguageItemType());
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                switch (actItem.getLanguageItemType()) {
                    case FUNDEF_IDENTIFIER_TERM, FUNDEF_VLIST_TERM -> {
                        TermNode term = new ASTTermParser().parse(fundef.getMyTerm());
                        ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
                        statements.add(new ReturnNode(term));
                        return new FunctionDeclarationNode(identifiers, statements);
                    }
                    case FUNDEF_IDENTIFIER_FUNCBODY, FUNDEF_VLIST_FUNCBODY -> {
                        ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
                        switch (fundef.getMyFuncBody().getLanguageItemType()) {
                            // TODO change FuncBodyType and FuncBodyItem to one case
                            case FUNCBODY_STATEMENTS -> {
                                for (int i = 0; i < fundef.getMyFuncBody().getMyStatements().size(); i++) {
                                    statements.add(new ASTStatementParser().parse(fundef.getMyFuncBody().getMyStatements().get(i)));
                                }
                                return new FunctionDeclarationNode(identifiers, statements);
                            }
                            case FUNCBODY_RETURNS -> {
                                for (int i = 0; i < fundef.getMyFuncBody().getMyReturnStmnts().size(); i++) {
                                    statements.add(new ASTStatementParser().parse(fundef.getMyFuncBody().getMyReturnStmnts().get(i)));
                                }
                                return new FunctionDeclarationNode(identifiers, statements);
                            }
                            case FUNCBODY_RETURN -> {
                                statements.add(new ASTStatementParser().parse(fundef.getMyFuncBody().getMyReturnStmnt()));
                                return new FunctionDeclarationNode(identifiers, statements);
                            }
                        }
                        return new FunctionDeclarationNode(identifiers, statements);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }

            }
            case TERMR_DOT -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new TupleElementNode(left, right);
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
                case STATEMENT_IDENTIFIER, ORDINAL_NUMBER, TUPEL_EMPTY, TUPEL_ONE, TUPEL_MULTI, TUPEL_INTERVAL,
                        ORDINAL_QUOTEDSTRING, ORDINAL_NULL, TERM_FUNCALL, AGGREGATION_COUNT, AGGREGATION_AVERAGE,
                        DISTINCT_ITEM, LOOP_LOOPBODY, LOOP_STATEMENT, MARK_WITHIF, MARK_WITHOUTIF, TERMR_FILTER,
                        FUNDEF_IDENTIFIER_TERM, FUNDEF_IDENTIFIER_FUNCBODY, FUNDEF_VLIST_TERM, FUNDEF_VLIST_FUNCBODY -> {
                    output.add(item);
                }
                case TERM_BRACKET -> {
                    stack.push(item);
                }
                case TERMR_BRACKET -> {
                    while (!stack.isEmpty()
                            && !stack.peek().getLanguageItemType().equals(LanguageItemType.TERM_BRACKET)) {
                        output.add(stack.pop());
                    }
                    if (!stack.isEmpty()) stack.pop();
                }
                case OPERATOR_ADD, OPERATOR_SUBTRACT, OPERATOR_MULTIPLY, OPERATOR_DIVIDE,
                        OPERATOR_POWER, OPERATOR_DIV, OPERATOR_MOD, TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V, TERMR_DOT -> {
                    while (!stack.isEmpty()
                            && isHigherPrecedence(item.getLanguageItemType())) {
                        output.add(stack.pop());
                    }
                    stack.push(item);
                }
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
