package de.hskempten.tabulang.interpretTest;


import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Interpretation interpretation = new Interpretation();

        NumberNode n1 = new NumberNode(new BigDecimal("5"));
        NumberNode n2 = new NumberNode(new BigDecimal("6"));
        NumberNode n3 = new NumberNode(new BigDecimal("7"));
        NumberNode n4 = new NumberNode(new BigDecimal("10"));
        NumberNode n11 = new NumberNode(new BigDecimal("11"));
        StringNode sHa = new StringNode("ha");
        StringNode sLlo = new StringNode("llo");
        StringNode s0 = new StringNode("0");
        StringNode s1 = new StringNode("1");
        StringNode s2 = new StringNode("2");
        VariableNode v1 = new VariableNode("a");
        VariableNode v2 = new VariableNode("b");
        VariableNode v3 = new VariableNode("c");
        VariableNode v4 = new VariableNode("d");
        VariableNode v5 = new VariableNode("e");
        VariableNode v6 = new VariableNode("x");
        AddNode a1 = new AddNode(n1, n2);
        AddNode a2 = new AddNode(sHa, sLlo);
        SubtractNode sub1 = new SubtractNode(a1, n4);
        //System.out.println(sub1.evaluateNode(i));
        //System.out.println(a1.evaluateNode(i));
        //System.out.println(a2.evaluateNode(i));
        AddNode a3 = new AddNode(sHa, sLlo);
        //System.out.println(a3.evaluateNode(i));
        MultiplyNode m1 = new MultiplyNode(v1, v2);
        AssignmentNode as1 = new AssignmentNode(v1, n4);
        AssignmentNode as2 = new AssignmentNode(v2, as1);
        AssignmentNode as3 = new AssignmentNode(v1, as2);
        //as1.evaluateNode(interpretation);
        AssignmentNode as4 = new AssignmentNode(v4, m1);
        //as4.evaluateNode(i);
        GreaterThanNode g1 = new GreaterThanNode(v1, n2);
        GreaterThanNode g2 = new GreaterThanNode(n3, n2);
        //System.out.println(g1.evaluateNode(i));
        EqualsNode e1 = new EqualsNode(v2, v1);
        //System.out.println(e1.evaluateNode(i));
        SpreadNode sp1 = new SpreadNode(n1, n4);
        //System.out.println(sp1.evaluateNode(i));

        List<Object> objects1 = new ArrayList<>();
        objects1.add(n3);
        objects1.add(n1);
        objects1.add(n4);
        Tuple t1 = new Tuple(objects1);
        TupleNode tupleNode1 = new TupleNode(t1);
        List<Object> objects2 = new ArrayList<>();
        objects2.add(n3);
        objects2.add(n2);
        objects2.add(n4);
        Tuple t2 = new Tuple(objects2);
        TupleNode tupleNode2 = new TupleNode(t2);
        List<Object> objects3 = new ArrayList<>();
        objects3.add(n1);
        objects3.add(n3);
        objects3.add(n4);
        Tuple t3 = new Tuple(objects3);
        TupleNode tupleNode3 = new TupleNode(t3);
        List<Object> objects4 = new ArrayList<>();
        objects4.add(n1);
        objects4.add(n2);
        objects4.add(n3);
        Tuple t4 = new Tuple(objects4);
        TupleNode tupleNode4 = new TupleNode(t4);
        AssignmentNode as5 = new AssignmentNode(v5, tupleNode1);
        //as5.evaluateNode(i);
        //System.out.println(tupleNode2.toString());
        //tupleNode2.evaluateNode(i);
        TupleElementNode tupleElementNode1 = new TupleElementNode(tupleNode1, s0);
        //System.out.println(tupleElementNode1.evaluateNode(i));
        TupleElementNode tupleElementNode2 = new TupleElementNode(tupleElementNode1, s1);
        //System.out.println(tupleElementNode2.evaluateNode(i));
        //System.out.println(tupleNode2.toString());
        CountNode countNode1 = new CountNode(tupleNode2);
        //System.out.println(countNode1.evaluateNode(i));
        InTupleNode inTupleNode1 = new InTupleNode(v3, tupleNode1);
        //as1.evaluateNode(i);
        //System.out.println(tupleNode1);
        //System.out.println(tupleElementNode1.evaluateNode(i));
        //System.out.println(tupleNode1.evaluateNode(i));

        Table table1 = new Table(t1, t3);
        TableNode tableNode1 = new TableNode(table1);
        Table table2 = new Table(t1, t4);
        TableNode tableNode2 = new TableNode(table2);
        //System.out.println(tableNode1.evaluateNode(interpretation));
        CountHorizontalNode countHorizontalNode1 = new CountHorizontalNode(tableNode1);
        //System.out.println(countHorizontalNode1.evaluateNode(interpretation));
        CountHorizontalNode countHorizontalNode2 = new CountHorizontalNode(tupleNode2);
        //System.out.println(countHorizontalNode2.evaluateNode(interpretation));
        CountVerticalNode countVerticalNode1 = new CountVerticalNode(tableNode1);
        //System.out.println("Count vertical: " + countVerticalNode1.evaluateNode(interpretation));
        AverageNode averageNode1 = new AverageNode(s2, tableNode1);
        //System.out.println(tableNode1.toString());
        //System.out.println("Average: " + averageNode1.evaluateNode(interpretation));
        String[] stringArray = {"1"};
        DistinctFromNode distinctFromNode1 = new DistinctFromNode(tableNode1, stringArray);
        //System.out.println("Distinct: " + distinctFromNode1.evaluateNode(interpretation));
        IntersectNode intersectNode1 = new IntersectNode(tableNode1, tableNode2);
        //System.out.println("Intersection: " + intersectNode1.evaluateNode(interpretation));
        SubtractNode subtractNode2 = new SubtractNode(tableNode1, tableNode2);
        System.out.println("Subraction: " + subtractNode2.evaluateNode(interpretation));
        UniteNode uniteNode1 = new UniteNode(tableNode1, tableNode2);
        //System.out.println("Unite: " + uniteNode1.evaluateNode(interpretation));



        //Conjoined Predicates
        /*NotNode not1 = new NotNode(g2);
        AndNode and1 = new AndNode(g1, g2);
        OrNode or1 = new OrNode(g1, g2);
        XorNode xor1 = new XorNode(g1, g2);
        ImplNode impl1 = new ImplNode(g1, g2);
        System.out.println(g2.evaluateNode(interpretation));
        System.out.println("Not: " + not1.evaluateNode(interpretation));
        System.out.println();
        System.out.println(g1.evaluateNode(interpretation));
        System.out.println(g2.evaluateNode(interpretation));
        System.out.println("And: " + and1.evaluateNode(interpretation));
        System.out.println("Or: " + or1.evaluateNode(interpretation));
        System.out.println("Xor: " + xor1.evaluateNode(interpretation));
        System.out.println("Impl: " + impl1.evaluateNode(interpretation));

        //exists x in t suchThat x > 6
        AssignmentNode a5 = new AssignmentNode(v6, n4);
        //a5.evaluateNode(interpretation);
        GreaterThanNode g3 = new GreaterThanNode(v6, new NumberNode(new BigDecimal("7")));
        ExistsSuchThatNode existsSuchThatNode1 = new ExistsSuchThatNode(tupleNode1, g3, "x");
        ForAllSuchThatNode forAllSuchThatNode1 = new ForAllSuchThatNode(tupleNode1, g3, "x");
        //System.out.println("ExistsSuchThat: " + existsSuchThatNode1.evaluateNode(interpretation));
        System.out.println("ForAllSuchThat: " + forAllSuchThatNode1.evaluateNode(interpretation));*/



        /*System.out.println(tupleNode2.toString());
        tupleNode2.evaluateNode(i);
        System.out.println(tupleNode2.toString());*/

        System.out.println();
        System.out.println(".......................");
        System.out.println("Environment: ");
        Iterator it = interpretation.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

    }
}
