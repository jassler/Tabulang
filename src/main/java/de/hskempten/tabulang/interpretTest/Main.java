package de.hskempten.tabulang.interpretTest;


import de.hskempten.tabulang.astNodes.*;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Interpretation i = new Interpretation();

        NumberNode n1 = new NumberNode(new BigDecimal("5"));
        NumberNode n2 = new NumberNode(new BigDecimal("6"));
        NumberNode n3 = new NumberNode(new BigDecimal("7"));
        NumberNode n4 = new NumberNode(new BigDecimal("10"));
        StringNode s1 = new StringNode("hi");
        StringNode s2 = new StringNode("ho");
        VariableNode v1 = new VariableNode("a");
        VariableNode v2 = new VariableNode("b");
        VariableNode v3 = new VariableNode("c");
        VariableNode v4 = new VariableNode("d");
        AddNode a1 = new AddNode(n1, n2);
        AddNode a2 = new AddNode(a1, s1);
        SubtractNode sub1 = new SubtractNode(a1, n4);
        System.out.println(sub1.evaluateNode(i));
        //System.out.println(a1.evaluateNode(i));
        System.out.println(a2.evaluateNode(i));
        AddNode a3 = new AddNode(s1, s2);
        //System.out.println(a3.evaluateNode(i));
        MultiplyNode m1 = new MultiplyNode(v1, v2);
        AssignmentNode as1 = new AssignmentNode(v3, n3);
        AssignmentNode as2 = new AssignmentNode(v2, as1);
        AssignmentNode as3 = new AssignmentNode(v1, as2);
        //as3.evaluateNode(i);
        AssignmentNode as4 = new AssignmentNode(v4, m1);
        //as4.evaluateNode(i);
        GreaterThanNode g1 = new GreaterThanNode(v2, v1);
        //System.out.println(g1.evaluateNode(i));
        EqualsNode e1 = new EqualsNode(v2, v1);
        //System.out.println(e1.evaluateNode(i));
        SpreadNode sp1 = new SpreadNode(n1, n4);
        //System.out.println(sp1.evaluateNode(i));

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
