package de.hskempten.tabulang.exampleTest;

import de.hskempten.tabulang.exampleTest.nonTerminal.*;
import de.hskempten.tabulang.exampleTest.terminal.IdentifierItem;
import de.hskempten.tabulang.exampleTest.terminal.NumberItem;
import de.hskempten.tabulang.exampleTest.terminal.OperatorItem;
import de.hskempten.tabulang.symbols.CloseParenthesis;
import de.hskempten.tabulang.symbols.ColonEquals;
import de.hskempten.tabulang.symbols.OpenParenthesis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<StatementItem> asi = new ArrayList<StatementItem>();

        //b := 2
        Number numberOne = new BigDecimal("2");
        String stringOne = "b";
        ColonEquals colonEqualsOne = new ColonEquals();
        TermRItem termRItemOne = new TermRItem();
        NumberItem numberItemOne = new NumberItem(numberOne);
        OrdinalItem ordinalItemOne = new OrdinalItem(numberItemOne);
        TermItem termItemOne = new TermItem(termRItemOne, ordinalItemOne);
        IdentifierItem identifierItemOne = new IdentifierItem(stringOne);
        VarDefItem varDefItemOne = new VarDefItem(identifierItemOne, colonEqualsOne, termItemOne);
        StatementItem statementItemOne = new StatementItem(varDefItemOne);
        asi.add(statementItemOne);

        //a := 7
        Number numberTwo = new BigInteger("11");
        String stringTwo = "a";
        ColonEquals colonEqualsTwo = new ColonEquals();
        TermRItem termRItemTwo = new TermRItem();
        NumberItem numberItemTwo = new NumberItem(numberTwo);
        OrdinalItem ordinalItemTwo = new OrdinalItem(numberItemTwo);
        TermItem termItemTwo = new TermItem(termRItemTwo, ordinalItemTwo);
        IdentifierItem identifierItemTwo = new IdentifierItem(stringTwo);
        VarDefItem varDefItemTwo = new VarDefItem(identifierItemTwo, colonEqualsTwo, termItemTwo);
        StatementItem statementItemTwo = new StatementItem(varDefItemTwo);
        asi.add(statementItemTwo);

        //a := 9
        /*Number numberThree = new BigInteger("9");
        String stringThree = "a";
        TermRItem termRItemThree = new TermRItem();
        NumberItem numberItemThree = new NumberItem(numberThree);
        OrdinalItem ordinalItemThree = new OrdinalItem(numberItemThree);
        TermItem termItemThree = new TermItem(termRItemThree, ordinalItemThree);
        IdentifierItem identifierItemThree = new IdentifierItem(stringThree);
        VarDefItem varDefItemThree = new VarDefItem(identifierItemThree, termItemThree);
        StatementItem statementItemThree = new StatementItem(varDefItemThree);
        asi.add(statementItemThree);*/

        //d := 5 + 4
        /*String stringFour = "d";
        IdentifierItem identifierItemFour = new IdentifierItem(stringFour);
        Number numberFour = new BigInteger("4");
        Number numberFive = new BigInteger("5");
        NumberItem numberItemFour = new NumberItem(numberFour);
        NumberItem numberItemFive = new NumberItem(numberFive);
        OrdinalItem ordinalItemFour = new OrdinalItem(numberItemFour);
        OrdinalItem ordinalItemFive = new OrdinalItem(numberItemFive);
        TermRItem termRItemFour = new TermRItem();
        TermRItem termRItemFive = new TermRItem();
        OperatorItem operatorItemOne = new OperatorItem("+");
        TermItem termItemFour = new TermItem(termRItemFour, ordinalItemFour);
        TermRItem termRItemSix = new TermRItem(termRItemFive, termItemFour, operatorItemOne);
        TermItem termItemFive = new TermItem(termRItemSix, ordinalItemFive);
        VarDefItem varDefItemFour = new VarDefItem(identifierItemFour, termItemFive);
        StatementItem statementItemFour = new StatementItem(varDefItemFour);
        asi.add(statementItemFour);*/

        //d := b + 5
        /*String stringFour = "d";
        String stringFive = "b";
        IdentifierItem identifierItemFour = new IdentifierItem(stringFour);
        IdentifierItem identifierItemFive = new IdentifierItem(stringFive);
        Number numberFour = new BigDecimal("5");
        NumberItem numberItemFour = new NumberItem(numberFour);
        OrdinalItem ordinalItemFour = new OrdinalItem(numberItemFour);
        TermRItem termRItemFour = new TermRItem();
        TermRItem termRItemFive = new TermRItem();
        OperatorItem operatorItemOne = new OperatorItem("+");
        TermItem termItemFour = new TermItem(termRItemFour, ordinalItemFour);
        TermRItem termRItemSix = new TermRItem(termRItemFive, termItemFour, operatorItemOne);
        TermItem termItemFive = new TermItem(termRItemSix, identifierItemFive);
        VarDefItem varDefItemFour = new VarDefItem(identifierItemFour, termItemFive);
        StatementItem statementItemFour = new StatementItem(varDefItemFour);
        asi.add(statementItemFour);*/

        //d := "Hi" + 5
        /*String stringFour = "d";
        String stringFive = "Hi";
        IdentifierItem identifierItemFour = new IdentifierItem(stringFour);
        QuotedStringItem quotedStringItemOne = new QuotedStringItem(stringFive);
        Number numberFour = new BigDecimal("5");
        NumberItem numberItemFour = new NumberItem(numberFour);
        OrdinalItem ordinalItemFour = new OrdinalItem(numberItemFour);
        OrdinalItem ordinalItemFive = new OrdinalItem(quotedStringItemOne);
        TermRItem termRItemFour = new TermRItem();
        TermRItem termRItemFive = new TermRItem();
        OperatorItem operatorItemOne = new OperatorItem("+");
        TermItem termItemFour = new TermItem(termRItemFour, ordinalItemFour);
        TermRItem termRItemSix = new TermRItem(termRItemFive, termItemFour, operatorItemOne);
        TermItem termItemFive = new TermItem(termRItemSix, ordinalItemFive);
        VarDefItem varDefItemFour = new VarDefItem(identifierItemFour, termItemFive);
        StatementItem statementItemFour = new StatementItem(varDefItemFour);
        asi.add(statementItemFour);*/

        //d := "Ha" + "llo"
        /*String stringFour = "d";
        String stringFive = "Ha";
        String stringSix = "llo";
        IdentifierItem identifierItemFour = new IdentifierItem(stringFour);
        QuotedStringItem quotedStringItemTwo = new QuotedStringItem(stringSix);
        QuotedStringItem quotedStringItemOne = new QuotedStringItem(stringFive);
        OrdinalItem ordinalItemFour = new OrdinalItem(quotedStringItemTwo);
        OrdinalItem ordinalItemFive = new OrdinalItem(quotedStringItemOne);
        TermRItem termRItemFour = new TermRItem();
        TermRItem termRItemFive = new TermRItem();
        OperatorItem operatorItemOne = new OperatorItem("+");
        TermItem termItemFour = new TermItem(termRItemFour, ordinalItemFour);
        TermRItem termRItemSix = new TermRItem(termRItemFive, termItemFour, operatorItemOne);
        TermItem termItemFive = new TermItem(termRItemSix, ordinalItemFive);
        VarDefItem varDefItemFour = new VarDefItem(identifierItemFour, termItemFive);
        StatementItem statementItemFour = new StatementItem(varDefItemFour);
        asi.add(statementItemFour);*/

        //e := 6 + 7 + 8
        /*Number eight = new BigDecimal("8");
        NumberItem numberItemEight = new NumberItem(eight);
        Number seven = new BigDecimal("7");
        OperatorItem operatorItemTwo = new OperatorItem("+");
        OrdinalItem ordinalItemEight = new OrdinalItem(numberItemEight);
        TermRItem termRItemEight = new TermRItem();
        NumberItem numberItemSeven = new NumberItem(seven);
        TermItem termItemEight = new TermItem(termRItemEight, ordinalItemEight);
        TermRItem termRItemNine = new TermRItem();
        OrdinalItem ordinalItemSeven = new OrdinalItem(numberItemSeven);
        TermRItem termRItemTen = new TermRItem(termRItemNine, termItemEight, operatorItemTwo);
        Number six = new BigDecimal("6");
        NumberItem numberItemSix = new NumberItem(six);
        OperatorItem operatorItemThree = new OperatorItem("+");
        TermItem termItemSix = new TermItem(termRItemTen, ordinalItemSeven);
        TermRItem termRItemEleven = new TermRItem();
        OrdinalItem ordinalItemSix = new OrdinalItem(numberItemSix);
        TermRItem termRItemTwelve = new TermRItem(termRItemEleven, termItemSix, operatorItemThree);
        TermItem termItemNine = new TermItem(termRItemTwelve, ordinalItemSix);
        String e = "e";
        IdentifierItem identifierItem = new IdentifierItem(e);
        VarDefItem varDefItem = new VarDefItem(identifierItem, termItemNine);
        StatementItem statementItem = new StatementItem(varDefItem);
        asi.add(statementItem);*/

        //e := b + a * 8
        /*Number eight = new BigDecimal("8");
        NumberItem numberItemEight = new NumberItem(eight);
        ColonEquals colonEqualsThree = new ColonEquals();
        OperatorItem operatorItemTwo = new OperatorItem("*");
        OrdinalItem ordinalItemEight = new OrdinalItem(numberItemEight);
        TermRItem termRItemEight = new TermRItem();
        TermItem termItemEight = new TermItem(termRItemEight, ordinalItemEight);
        TermRItem termRItemNine = new TermRItem();
        TermRItem termRItemTen = new TermRItem(termRItemNine, termItemEight, operatorItemTwo);
        OperatorItem operatorItemThree = new OperatorItem("+");
        TermRItem termRItemEleven = new TermRItem();
        IdentifierItem identifierItem1 = new IdentifierItem(stringOne);
        IdentifierItem identifierItem2 = new IdentifierItem(stringTwo);
        TermItem termItemSix = new TermItem(termRItemTen, identifierItem2);
        TermRItem termRItemTwelve = new TermRItem(termRItemEleven, termItemSix, operatorItemThree);
        TermItem termItemNine = new TermItem(termRItemTwelve, identifierItem1);
        String e = "e";
        IdentifierItem identifierItem = new IdentifierItem(e);
        VarDefItem varDefItem = new VarDefItem(identifierItem, colonEqualsThree, termItemNine);
        StatementItem statementItem = new StatementItem(varDefItem);
        asi.add(statementItem);*/

        //f := (b + a) * 8
        Number n = new BigDecimal("8");
        NumberItem ni = new NumberItem(n);
        IdentifierItem b = new IdentifierItem(stringOne);
        IdentifierItem a = new IdentifierItem(stringTwo);
        OperatorItem plus = new OperatorItem("+");
        TermRItem r8 = new TermRItem();
        TermRItem ra = new TermRItem();
        TermItem trplus = new TermItem(ra, a);
        TermRItem rtrplus = new TermRItem();
        OperatorItem star = new OperatorItem("*");
        OrdinalItem rstar = new OrdinalItem(ni);
        TermRItem rb = new TermRItem(rtrplus, trplus, plus);
        TermItem trstar = new TermItem(r8, rstar);
        TermRItem rtrstar = new TermRItem();
        OpenParenthesis op = new OpenParenthesis();
        TermItem ropenP = new TermItem(rb, b);
        CloseParenthesis cp = new CloseParenthesis();
        TermRItem rcloseP = new TermRItem(rtrstar, trstar, star);
        IdentifierItem f = new IdentifierItem("f");
        ColonEquals ce = new ColonEquals();
        TermItem rce = new TermItem(op, ropenP, cp, rcloseP);
        VarDefItem vd = new VarDefItem(f, ce, rce);
        StatementItem se = new StatementItem(vd);
        asi.add(se);



        ProgramItem pi = new ProgramItem(asi);

        Interpretation i = new Interpretation();

        pi.traverse(i);

        System.out.println();
        System.out.println(".......................");
        System.out.println("Environment: ");
        Iterator it = i.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

    }
}
