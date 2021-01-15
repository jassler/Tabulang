package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.PositionedException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static de.hskempten.tabulang.items.LanguageItemType.*;

public class ASTTermParser {

    ShuntingYardBuilder syBuilder = new ShuntingYardBuilder();
    private int nestingLevel = 0;

    public TermNode parse(TermItem originalTerm, int nestingLevel) throws PositionedException {
        this.nestingLevel = nestingLevel;
        return parse(originalTerm);
    }

    public TermNode parse(TermItem originalTerm) throws PositionedException {

        traverseTerm(originalTerm);
        TermNode parsedTerm = termParser(syBuilder.getOutput());

        return parsedTerm;
    }

    private void traverseTerm(TermItem originalTerm) throws PositionedException {
        TermOrRItem actTerm = originalTerm;
        while (true) {
            if (Arrays.asList(TERM_IDENTIFIER, TERM_ORDINAL).contains(actTerm.getLanguageItemType())
                    && Arrays.asList(TERMR_FILTER, TERMR_INTERSECT, TERMR_UNITE, TERMR_MARK).contains(((TermItem) actTerm).getMyTermR().getLanguageItemType())) {

                TermRItem termR = actTerm.getMyTermR();
                termR.setMyPreviousTerm((TermItem) actTerm);
                syBuilder.add(termR);

                actTerm = termR.getMyTermR();

            } else {
                switch (actTerm.getLanguageItemType()) {
                    case TERM_IDENTIFIER -> {
                        syBuilder.add(((TermItem) actTerm).getMyIdentifier());
                    }
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
                        syBuilder.add((LanguageItemAbstract) actTerm);
                        traverseTerm(((TermItem) actTerm).getMyTerm());
                    }
                    case TERMR_BRACKET -> {
                        syBuilder.add((LanguageItemAbstract) actTerm);
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
                        syBuilder.add((LanguageItemAbstract) actTerm);
                    }
                    case TERM_FUNDEF -> {
                        syBuilder.add(((TermItem) actTerm).getMyFunDef());
                    }
                    case TERMR_DOT -> {
                        syBuilder.add((LanguageItemAbstract) actTerm);
                        traverseTerm(((TermRItem) actTerm).getMyTerm());
                    }
                    default -> {
                        throw new ParseTimeException("ASTTermParser case not yet implemented: " + actTerm.getLanguageItemType(), originalTerm.getTextPosition());
                    }
                }
                switch (actTerm.getLanguageItemType()) {
                    case TERM_IDENTIFIER, TERM_ORDINAL, TERM_DIRECTIONAL, TERM_FUNCALL, TERM_AGGREGATION,
                            TERM_DISTINCT, TERM_LOOP, TERMR_MARK, TERMR_FILTER, TERM_FUNDEF, TERMR_DOT, TERM_BRACKET -> actTerm = actTerm.getMyTermR();
                    case TERMR_OPERATOR -> actTerm = ((TermRItem) actTerm).getMyTerm();
                    default -> {
                        throw new ParseTimeException("Unexpected value: " + actTerm.getLanguageItemType(), originalTerm.getTextPosition());
                    }
                }
            }
        }
    }

    private TermNode termParser(ArrayList<LanguageItemAbstract> items) throws PositionedException {


        LanguageItemAbstract actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);

        TextPosition textPosition = actItem.getTextPosition();
        switch (actItem.getLanguageItemType()) {
            case OPERATOR_ADD, OPERATOR_SUBTRACT, OPERATOR_MULTIPLY, OPERATOR_MOD,
                    OPERATOR_DIV, OPERATOR_DIVIDE, OPERATOR_POWER -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                textPosition = new TextPosition(right.getTextPosition(), left.getTextPosition());
                switch (actItem.getLanguageItemType()) {
                    case OPERATOR_ADD:
                        return new AddNode(left, right, textPosition);
                    case OPERATOR_SUBTRACT:
                        return new SubtractNode(left, right, textPosition);
                    case OPERATOR_MULTIPLY:
                        return new MultiplyNode(left, right, textPosition);
                    case OPERATOR_MOD:
                        return new ModNode(left, right, textPosition);
                    case OPERATOR_DIV:
                        return new DivNode(left, right, textPosition);
                    case OPERATOR_DIVIDE:
                        return new DivisionNode(left, right, textPosition);
                    case OPERATOR_POWER:
                        return new PowerNode(left, right, textPosition);
                    default:
                        throw new ParseTimeException("Unexpected value: " + actItem.getLanguageItemType(), actItem.getTextPosition());
                }
            }
            case ORDINAL_NUMBER -> {
                NumberItem nItem = (NumberItem) actItem;
                return new NumberNode(nItem.getNumerator(), nItem.getDenominator(), textPosition);
            }
            case STATEMENT_IDENTIFIER -> {
                return new IdentifierNode(((IdentifierItem) actItem).getMyString(), textPosition);
            }
            case TUPEL_EMPTY -> {
                return new TupleNode(textPosition);
            }
            case TUPEL_ONE -> {
                TupelItem tupel = (TupelItem) actItem;
                TermNode term = new ASTTermParser().parse(tupel.getMyTerm());
                return new TupleNode(term, textPosition);
            }
            case TUPEL_MULTI -> {
                TupelItem tupel = (TupelItem) actItem;
                ArrayList<TermNode> terms = new ArrayList<TermNode>();
                terms.add(new ASTTermParser().parse(tupel.getMyTerm()));
                for (int i = 0; i < tupel.getMyTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(tupel.getMyTerms().get(i)));
                }
                return new TupleNode(terms, textPosition);
            }
            case TUPEL_INTERVAL -> {
                TupelItem tupel = (TupelItem) actItem;
                TermNode firstTerm = new ASTTermParser().parse(tupel.getMyIntervall().getMyTerm());
                TermNode secondTerm = new ASTTermParser().parse(tupel.getMyIntervall().getMySecondTerm());
                return new SpreadNode(firstTerm, secondTerm, textPosition);
            }
            case ORDINAL_QUOTEDSTRING -> {
                String string = ((QuotedStringItem) actItem).getMyString();
                return new StringNode(string, textPosition);
            }
            case ORDINAL_NULL -> {
                return new NullNode(textPosition);
            }
            case TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V -> {
                TermNode item;
                TermNode term = new ASTTermParser().parse(((DirectionalTermItem) actItem).getMyTerm());
                switch (actItem.getLanguageItemType()) {
                    case TERM_DIRECTIONAL_H -> item = new HorizontalTupleNode(term, textPosition);
                    case TERM_DIRECTIONAL_V -> item = new VerticalTupleNode(term, textPosition);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                return item;
            }
            case TERM_FUNCALL -> {
                IdentifierNode identifier = new IdentifierNode(((FunCallItem) actItem).getMyIdentifier().getMyString(), textPosition);
                ArrayList<TermNode> terms = new ArrayList<TermNode>();
                for (int i = 0; i < ((FunCallItem) actItem).getTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(((FunCallItem) actItem).getTerms().get(i)));
                }
                return new FunctionCallNode(identifier, terms, textPosition);
            }
            case AGGREGATION_AVERAGE -> {
                AverageTItem ave = ((AggregationTItem) actItem).getMyAverageT();
                IdentifierNode identifier = new IdentifierNode(ave.getMyIdentifier().getMyString(), textPosition);
                TermNode term = new ASTTermParser().parse(ave.getMyTerm());
                return new AverageNode(identifier, term, textPosition);

            }
            case AGGREGATION_COUNT -> {
                CountTItem cnt = ((AggregationTItem) actItem).getMyCountT();
                TermNode term = new ASTTermParser().parse(cnt.getMyTerm());
                switch (cnt.getLanguageItemType()) {
                    case COUNT_EMPTY -> {
                        return new CountNode(term, textPosition);
                    }
                    case COUNT_DIRECTIONAL -> {
                        switch (cnt.getMyString()) {
                            case "horizontal":
                                return new CountHorizontalNode(term, textPosition);
                            case "vertical":
                                return new CountVerticalNode(term, textPosition);
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
                    identifiers.add(new IdentifierNode(dis.getMyIdentifiers().get(i).getMyString(), textPosition));
                }
                TermNode term = new ASTTermParser().parse(dis.getMyTerm());
                return new DistinctFromNode(term, identifiers, textPosition);
            }
            case LOOP_LOOPBODY, LOOP_STATEMENT -> {
                LoopItem loop = (LoopItem) actItem;
                IdentifierNode identifier = new IdentifierNode(loop.getMyIdentifier().getMyString(), textPosition);
                TermNode term = new ASTTermParser().parse(loop.getMyTerm());
                ArrayList<Node> statements = new ArrayList<Node>();
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
                return new LoopTermNode(identifier, term, statements, nestingLevel + 1, textPosition);
            }
            case TERMR_MARK -> {
                TermItem previousTerm = ((TermRItem) actItem).getMyPreviousTerm();
                MarkStmntItem mark = ((TermRItem) actItem).getMyMarkStmnt();
                TermNode markTerm = new ASTTermParser().parse(mark.getMyTerm());
                TermNode asTerm = new ASTTermParser().parse(mark.getMySecondTerm());
                switch (mark.getLanguageItemType()) {
                    case MARK_WITHOUTIF -> {
                        previousTerm.getMyTermR().setLanguageItemType(TERMR_NULL);
                        TermNode prevTerm = new ASTTermParser().parse(previousTerm);
                        return new MarkTermNode(prevTerm, markTerm, asTerm, textPosition);
                    }
                    case MARK_WITHIF -> {
                        previousTerm.getMyTermR().setLanguageItemType(TERMR_NULL);
                        TermNode prevTerm = new ASTTermParser().parse(previousTerm);
                        PredicateNode pred = new ASTPredParser().parse(mark.getMyPred());
                        return new MarkIfTermNode(prevTerm, markTerm, asTerm, pred, textPosition);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case TERMR_FILTER -> {
                TermItem previousTerm = ((TermRItem) actItem).getMyPreviousTerm();
                previousTerm.getMyTermR().setLanguageItemType(TERMR_NULL);
                TermNode prevTerm = new ASTTermParser().parse(previousTerm);
                PredicateNode pred = new ASTPredParser().parse(((TermRItem) actItem).getMyPred());
                return new FilterNode(prevTerm, pred, textPosition);
            }
            case TERMR_INTERSECT -> {
                TermItem previousTerm = ((TermRItem) actItem).getMyPreviousTerm();
                previousTerm.getMyTermR().setLanguageItemType(TERMR_NULL);
                TermNode prevTerm = new ASTTermParser().parse(previousTerm);
                TermNode term = new ASTTermParser().parse(((TermRItem) actItem).getMyTerm());
                return new IntersectNode(prevTerm, term, textPosition);
            }
            case TERMR_UNITE -> {
                TermItem previousTerm = ((TermRItem) actItem).getMyPreviousTerm();
                previousTerm.getMyTermR().setLanguageItemType(TERMR_NULL);
                TermNode prevTerm = new ASTTermParser().parse(previousTerm);
                TermNode term = new ASTTermParser().parse(((TermRItem) actItem).getMyTerm());
                return new UniteNode(prevTerm, term, textPosition);
            }
            case FUNDEF_IDENTIFIER_TERM, FUNDEF_IDENTIFIER_FUNCBODY, FUNDEF_VLIST_TERM, FUNDEF_VLIST_FUNCBODY -> {
                FunDefItem fundef = (FunDefItem) actItem;
                ArrayList<IdentifierNode> identifiers = new ArrayList<IdentifierNode>();
                switch (actItem.getLanguageItemType()) {
                    case FUNDEF_IDENTIFIER_TERM, FUNDEF_IDENTIFIER_FUNCBODY -> {
                        identifiers.add(new IdentifierNode(fundef.getMyIdentifier().getMyString(), textPosition));
                    }
                    case FUNDEF_VLIST_TERM, FUNDEF_VLIST_FUNCBODY -> {
                        switch (fundef.getMyVList().getLanguageItemType()) {
                            case VLIST_ONE, VLIST_MULTI -> {
                                identifiers.add(new IdentifierNode(fundef.getMyVList().getMyIdentifier().getMyString(), textPosition));
                                if (LanguageItemType.VLIST_MULTI.equals(fundef.getMyVList().getLanguageItemType())) {
                                    for (int i = 0; i < fundef.getMyVList().getMyOtherIdentifiers().size(); i++) {
                                        identifiers.add(new IdentifierNode(fundef.getMyVList().getMyOtherIdentifiers().get(i).getMyString(), textPosition));
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
                        statements.add(new ReturnNode(term, textPosition));
                        return new FunctionDeclarationNode(identifiers, statements, textPosition);
                    }
                    case FUNDEF_IDENTIFIER_FUNCBODY, FUNDEF_VLIST_FUNCBODY -> {
                        ArrayList<StatementNode> statements = new ArrayList<StatementNode>();

                        for (int i = 0; i < fundef.getMyFuncBody().getMyStatements().size(); i++) {
                            statements.add((StatementNode) new ASTStatementParser().parse(fundef.getMyFuncBody().getMyStatements().get(i)));
                        }
                        return new FunctionDeclarationNode(identifiers, statements, textPosition);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
            }
            case TERMR_DOT -> {
                TermNode right = termParser(items);
                TermNode left = termParser(items);
                return new TupleElementNode(left, right, textPosition);
            }
            default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
        }
    }


    private static class ShuntingYardBuilder {
        Stack<LanguageItemAbstract> stack;
        ArrayList<LanguageItemAbstract> output;

        public ShuntingYardBuilder() {
            this.stack = new Stack<LanguageItemAbstract>();
            this.output = new ArrayList<LanguageItemAbstract>();
        }

        public void add(LanguageItemAbstract item) {
            switch (item.getLanguageItemType()) {
                case STATEMENT_IDENTIFIER, ORDINAL_NUMBER, TUPEL_EMPTY, TUPEL_ONE, TUPEL_MULTI, TUPEL_INTERVAL,
                        ORDINAL_QUOTEDSTRING, ORDINAL_NULL, TERM_FUNCALL, AGGREGATION_COUNT, AGGREGATION_AVERAGE,
                        DISTINCT_ITEM, LOOP_LOOPBODY, LOOP_STATEMENT, MARK_WITHIF, MARK_WITHOUTIF, TERMR_FILTER,
                        TERMR_INTERSECT, TERMR_UNITE, TERMR_MARK, FUNDEF_IDENTIFIER_TERM,
                        FUNDEF_IDENTIFIER_FUNCBODY, FUNDEF_VLIST_TERM, FUNDEF_VLIST_FUNCBODY -> {
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

        public ArrayList<LanguageItemAbstract> getOutput() throws PositionedException {
            while (!stack.isEmpty()) {
                if (LanguageItemType.TERM_BRACKET.equals(stack.peek().getLanguageItemType())) {
                    throw new ParseTimeException("Shunting Yard Builder invalid", stack.peek().getTextPosition());
                }
                output.add(stack.pop());
            }
            return output;
        }

    }


}
