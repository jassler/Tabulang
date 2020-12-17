package de.hskempten.tabulang.interpretTest;


import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;

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
        StringNode sA = new StringNode("a");
        IdentifierNode v1 = new IdentifierNode(new Identifier("a"));
        IdentifierNode v2 = new IdentifierNode(new Identifier("b"));
        IdentifierNode v3 = new IdentifierNode(new Identifier("c"));
        IdentifierNode v4 = new IdentifierNode(new Identifier("d"));
        IdentifierNode v5 = new IdentifierNode(new Identifier("e"));
        IdentifierNode v6 = new IdentifierNode(new Identifier("x"));
        AddNode a1 = new AddNode(n1, n2);
        AddNode a2 = new AddNode(sHa, sLlo);
        SubtractNode sub1 = new SubtractNode(a1, n4);
        //System.out.println(sub1.evaluateNode(i));
        //System.out.println(a1.evaluateNode(i));
        //System.out.println(a2.evaluateNode(i));
        AddNode a3 = new AddNode(sHa, sLlo);
        //System.out.println(a3.evaluateNode(i));
        MultiplyNode m1 = new MultiplyNode(n1, n2);
        AssignmentNode as1 = new AssignmentNode(v1, n4, true);
        AssignmentNode as2 = new AssignmentNode(v2, as1, true);
        AssignmentNode as3 = new AssignmentNode(v3, as2, true);
        as3.evaluateNode(interpretation);
        AssignmentNode as4 = new AssignmentNode(v4, m1, true);
        //as4.evaluateNode(interpretation);
        GreaterThanNode g1 = new GreaterThanNode(n11, n2);
        GreaterThanNode g2 = new GreaterThanNode(n3, n2);
        //System.out.println(g1.evaluateNode(i));
        EqualsNode e1 = new EqualsNode(v2, v1);
        //System.out.println(e1.evaluateNode(i));
        SpreadNode sp1 = new SpreadNode(n1, n4);
        //System.out.println(sp1.evaluateNode(i));

        List<Object> objects1 = new ArrayList<>();
        objects1.add(n3);
        objects1.add(n1);
        objects1.add(n2);
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
        AssignmentNode as5 = new AssignmentNode(v5, tupleNode1, true);
        //as5.evaluateNode(i);
        //System.out.println(tupleNode2.toString());
        //tupleNode2.evaluateNode(i);
        StringNode s4 = new StringNode("4");
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
        //System.out.println("Subtraction: " + subtractNode2.evaluateNode(interpretation));
        UniteNode uniteNode1 = new UniteNode(tableNode1, tableNode2);
        //System.out.println("Unite: " + uniteNode1.evaluateNode(interpretation));



        //Conjoined Predicates
        /*NotNode not1 = new NotNode(g2);
        AndNode and1 = new AndNode(g1, g2);
        OrNode or1 = new OrNode(g1, g2);
        XorNode xor1 = new XorNode(g1, g2);
        IffNode iff1 = new IffNode(g1, g2);
        ImplNode impl1 = new ImplNode(g1, g2);
        System.out.println("G1: " + g1.evaluateNode(interpretation));
        System.out.println("G2: " + g2.evaluateNode(interpretation));
        System.out.println("Not G2: " + not1.evaluateNode(interpretation));
        System.out.println();
        System.out.println("And: " + and1.evaluateNode(interpretation));
        System.out.println("Or: " + or1.evaluateNode(interpretation));
        System.out.println("Xor: " + xor1.evaluateNode(interpretation));
        System.out.println("Iff: " + iff1.evaluateNode(interpretation));
        System.out.println("Impl: " + impl1.evaluateNode(interpretation));*/

        //exists x in t suchThat x > 6
        AssignmentNode a5 = new AssignmentNode(v6, n4, true);
        //a5.evaluateNode(interpretation);
        GreaterThanNode g3 = new GreaterThanNode(v6, new NumberNode(new BigDecimal("7")));
        ExistsSuchThatNode existsSuchThatNode1 = new ExistsSuchThatNode(tupleNode1, g3, "x");
        ForAllSuchThatNode forAllSuchThatNode1 = new ForAllSuchThatNode(tupleNode1, g3, "x");
        //System.out.println(tupleNode1);
        //System.out.println(g3);
        //System.out.println("ExistsSuchThat: " + existsSuchThatNode1.evaluateNode(interpretation));
        //System.out.println("ForAllSuchThat: " + forAllSuchThatNode1.evaluateNode(interpretation));

        Identifier param1 = new Identifier("param1");
        IdentifierNode identifierNode1 = new IdentifierNode(param1);
        ArrayList<Object> parameters = new ArrayList<>();
        parameters.add(param1);
        ArrayList<Object> statements = new ArrayList<>();
        AddNode fAdd1 = new AddNode(identifierNode1, n11);
        AssignmentNode fAssignment1 = new AssignmentNode(identifierNode1, fAdd1, true);
        AddNode fAdd2 = new AddNode(identifierNode1, n3);
        AssignmentNode fAssignment2 = new AssignmentNode(identifierNode1, fAdd2, false);
        ReturnNode returnNode1 = new ReturnNode(identifierNode1);
        ReturnNode returnNode2 = new ReturnNode(identifierNode1);
        statements.add(fAssignment1);
        statements.add(returnNode1);
        statements.add(fAssignment2);
        //statements.add(returnNode1);

        FunctionDeclarationNode functionDeclarationNode1 = new FunctionDeclarationNode(parameters, statements);
        //System.out.println(functionDeclarationNode1.evaluateNode(interpretation));
        Identifier idFunction1 = new Identifier("myFirstFunction");
        IdentifierNode idNode1 = new IdentifierNode(idFunction1);
        AssignmentNode fAssignment3 = new AssignmentNode(idNode1, functionDeclarationNode1, true);
        fAssignment3.evaluateNode(interpretation);

        /*System.out.println(tupleNode2.toString());
        tupleNode2.evaluateNode(i);
        System.out.println(tupleNode2.toString());*/

        SetNode setNode1 = new SetNode(v1, 1);
        setNode1.evaluateNode(interpretation);

        GreaterThanNode greaterThanNode2 = new GreaterThanNode(v1, n3);
        //greaterThanNode2.evaluateNode(interpretation);
        FilterNode filterNode1 = new FilterNode(tableNode2, greaterThanNode2);
        //System.out.println(filterNode1.evaluateNode(interpretation));
        AssignmentNode a10 = new AssignmentNode(v6, v1 , false);
        //a10.evaluateNode(interpretation);

        ArrayList<Node> funcParameter = new ArrayList<>();
        funcParameter.add(n11);
        FunctionCallNode functionCallNode1 = new FunctionCallNode(idNode1, funcParameter);
        //functionCallNode1.evaluateNode(interpretation);
        AssignmentNode assignmentNodeFunction = new AssignmentNode(v6, functionCallNode1, true);
        assignmentNodeFunction.evaluateNode(interpretation);

        System.out.println("-.-.-.-.-.-.-.-");
        ArrayList<Node> funcParameter2 = new ArrayList<>();
        funcParameter2.add(n1);
        FunctionCallNode functionCallNode2 = new FunctionCallNode(idNode1, funcParameter2);
        //functionCallNode2.evaluateNode(interpretation);



        AddNode addNode3 = new AddNode(v1, n1);
        AssignmentNode assignmentNode3 = new AssignmentNode(v2, addNode3, true);
        AssignmentNode assignmentNode4 = new AssignmentNode(v1, assignmentNode3, false);
        //assignmentNode3.evaluateNode(interpretation);
        ArrayList<Object> blockArray = new ArrayList<>();
        blockArray.add(assignmentNode3);
        blockArray.add(assignmentNode4);
        BlockNode blockNode1 = new BlockNode(blockArray);
        //blockNode1.evaluateNode(interpretation);

        System.out.println(tupleElementNode1);
        System.out.println(tupleElementNode1.evaluateNode(interpretation));

        System.out.println(sp1.evaluateNode(interpretation));

        //System.out.println(averageNode1.evaluateNode(interpretation));
        System.out.println(countVerticalNode1.evaluateNode(interpretation));









        System.out.println();
        System.out.println(".......................");
        System.out.println("Outer Environment: ");
        Iterator it = interpretation.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

    }
}
