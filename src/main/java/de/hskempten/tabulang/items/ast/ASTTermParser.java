package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;
import java.util.Stack;

public class ASTTermParser {

    ShuntingYardBuilder syBuilder = new ShuntingYardBuilder();

    public TermAST parse(TermItem originalTerm) throws Exception {

        traverseTerm(originalTerm);
        TermAST parsedTerm = termParser(syBuilder.getOutput());

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
                default -> {
                    throw new Exception("ASTTermParser case not yet implemented: " + actTerm.getLanguageItemType());
                }
            }
            actTerm = switch (actTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER, TERM_ORDINAL, TERM_DIRECTIONAL, TERM_FUNCALL, TERM_AGGREGATION,
                        TERM_DISTINCT, TERM_LOOP, TERMR_MARK, TERMR_FILTER -> actTerm.getMyTermR();
                case TERMR_OPERATOR -> ((TermRItem) actTerm).getMyTerm();
                case TERM_BRACKET -> actTerm.getMyTermR();
                default -> {
                    throw new IllegalStateException("Unexpected value: " + actTerm.getLanguageItemType());
                }
            };
            i--;
        }
    }

    private TermAST termParser(ArrayList<LanguageItem> items) throws Exception {


        LanguageItem actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);

        switch (actItem.getLanguageItemType()) {
            case OPERATOR_ADD -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new AddAST(left, right);
            }
            case OPERATOR_SUBTRACT -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new SubtractAST(left, right);
            }
            case OPERATOR_MULTIPLY -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new MultiplyAST(left, right);
            }
            case OPERATOR_MOD -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new ModAST(left, right);
            }
            case OPERATOR_DIV -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new DivAST(left, right);
            }
            case OPERATOR_DIVIDE -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new DivideAST(left, right);
            }
            case OPERATOR_POWER -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new PowerAST(left, right);
            }
            case ORDINAL_NUMBER -> {
                NumberItem nItem = (NumberItem) actItem;
                NumberAST item = new NumberAST(nItem.getNumerator(), nItem.getDenominator());

                return item;
            }
            case STATEMENT_IDENTIFIER -> {
                IdentifierAST item = new IdentifierAST(((IdentifierItem) actItem).getMyString());
                return item;
            }
            case TUPEL_EMPTY -> {
                TupelAST item = new TupelAST();
                return item;
            }
            case TUPEL_ONE -> {
                TupelItem tupel = (TupelItem) actItem;
                TermAST term = new ASTTermParser().parse(tupel.getMyTerm());
                TupelAST item = new TupelAST(term);
                return item;
            }
            case TUPEL_MULTI -> {
                TupelItem tupel = (TupelItem) actItem;
                ArrayList<TermAST> terms = new ArrayList<TermAST>();
                terms.add(new ASTTermParser().parse(tupel.getMyTerm()));
                for (int i = 0; i < tupel.getMyTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(tupel.getMyTerms().get(i)));
                }
                TupelAST item = new TupelAST(terms);
                return item;
            }
            case TUPEL_INTERVAL -> {
                TupelItem tupel = (TupelItem) actItem;
                TermAST firstTerm = new ASTTermParser().parse(tupel.getMyIntervall().getMyTerm());
                TermAST secondTerm = new ASTTermParser().parse(tupel.getMyIntervall().getMySecondTerm());
                return new IntervalAST(firstTerm, secondTerm);
            }
            case ORDINAL_QUOTEDSTRING -> {
                String string = ((QuotedStringItem) actItem).getMyString();
                QuotedStringAST item = new QuotedStringAST(string);
                return item;
            }
            case ORDINAL_NULL -> {
                NullAST item = new NullAST();
                return item;
            }
            case TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V -> {
                DirectionalAST item;
                TermAST term = new ASTTermParser().parse(((DirectionalTermItem) actItem).getMyTerm());
                switch (actItem.getLanguageItemType()) {
                    case TERM_DIRECTIONAL_H -> item = new DirectionalAST(DirectionalAST.Dir.HORIZONTAL, term);
                    case TERM_DIRECTIONAL_V -> item = new DirectionalAST(DirectionalAST.Dir.VERTICAL, term);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                return item;
            }
            case TERM_FUNCALL -> {
                IdentifierAST identifier = new IdentifierAST(((FunCallItem) actItem).getMyIdentifier().getMyString());
                ArrayList<TermAST> terms = new ArrayList<TermAST>();
                for (int i = 0; i < ((FunCallItem) actItem).getTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(((FunCallItem) actItem).getTerms().get(i)));
                }
                return new FunCallAST(identifier, terms);
            }
            case AGGREGATION_AVERAGE -> {
                AverageTItem ave = ((AggregationTItem) actItem).getMyAverageT();
                IdentifierAST identifier = new IdentifierAST(ave.getMyIdentifier().getMyString());
                TermAST term = new ASTTermParser().parse(ave.getMyTerm());
                return new AverageAST(identifier, term);

            }
            case AGGREGATION_COUNT -> {
                CountTItem cnt = ((AggregationTItem) actItem).getMyCountT();
                TermAST term = new ASTTermParser().parse(cnt.getMyTerm());
                switch (cnt.getLanguageItemType()) {
                    case COUNT_EMPTY -> {
                        return new CountAST(CountAST.Dir.NONE, term);
                    }
                    case COUNT_DIRECTIONAL -> {
                        switch (cnt.getMyString()) {
                            case "horizontal":
                                return new CountAST(CountAST.Dir.HORIZONTAL, term);
                            case "vertical":
                                return new CountAST(CountAST.Dir.VERTICAL, term);
                            default:
                                throw new IllegalStateException("Unexpected value: " + cnt.getMyString());
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + cnt.getLanguageItemType());
                }
            }
            case DISTINCT_ITEM -> {
                DistinctTItem dis = (DistinctTItem) actItem;
                ArrayList<IdentifierAST> identifiers = new ArrayList<IdentifierAST>();
                for (int i = 0; i < dis.getMyIdentifiers().size(); i++) {
                    identifiers.add(new IdentifierAST(dis.getMyIdentifiers().get(i).getMyString()));
                }
                TermAST term = new ASTTermParser().parse(dis.getMyTerm());
                return new DistinctAST(identifiers, term);
            }
            case LOOP_LOOPBODY, LOOP_STATEMENT -> {
                LoopItem loop = (LoopItem) actItem;
                IdentifierAST identifier = new IdentifierAST(loop.getMyIdentifier().getMyString());
                TermAST term = new ASTTermParser().parse(loop.getMyTerm());
                ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
                switch (actItem.getLanguageItemType()) {
                    case LOOP_LOOPBODY -> {
                        for (int i = 0; i < loop.getMyLoopBody().getMyLoopStmnts().size(); i++) {
                            statements.add(new ASTStatementParser().parse(loop.getMyLoopBody().getMyLoopStmnts().get(i)));
                        }
                    }
                    case LOOP_STATEMENT -> {
                        statements.add(new ASTStatementParser().parse(loop.getMyLoopStmnt()));
                    }
                }
                return new LoopAST(identifier, term, statements);
            }
            case MARK_WITHIF, MARK_WITHOUTIF -> {
                MarkStmntItem mark = (MarkStmntItem) actItem;
                TermAST markTerm = new ASTTermParser().parse(mark.getMyTerm());
                TermAST asTerm = new ASTTermParser().parse(mark.getMySecondTerm());
                switch (actItem.getLanguageItemType()) {
                    case MARK_WITHOUTIF -> {
                        return new StatementMarkAST(markTerm, asTerm);
                    }
                    case MARK_WITHIF -> {
                        PredAST pred = new ASTPredParser().parse(mark.getMyPred());
                        return new StatementMarkIfAST(markTerm, asTerm, pred);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case TERMR_FILTER -> {
                ArrayList<PredAST> preds = new ArrayList<PredAST>();
                return new FilterAST(preds);
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
                        DISTINCT_ITEM, LOOP_LOOPBODY, LOOP_STATEMENT, MARK_WITHIF, MARK_WITHOUTIF, TERMR_FILTER -> {
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
                        OPERATOR_POWER, OPERATOR_DIV, OPERATOR_MOD, TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V -> {
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
