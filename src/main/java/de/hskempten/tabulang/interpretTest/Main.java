package de.hskempten.tabulang.interpretTest;


import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.astNodes.term.arithmetic.AddNode;
import de.hskempten.tabulang.astNodes.term.arithmetic.MultiplyNode;
import de.hskempten.tabulang.astNodes.term.arithmetic.NumberNode;
import de.hskempten.tabulang.astNodes.term.arithmetic.SubtractNode;
import de.hskempten.tabulang.astNodes.predicate.*;
import de.hskempten.tabulang.astNodes.statement.*;
import de.hskempten.tabulang.astNodes.term.*;
import de.hskempten.tabulang.astNodes.tupleOperations.CountNode;
import de.hskempten.tabulang.astNodes.tupleOperations.SpreadNode;
import de.hskempten.tabulang.astNodes.tupleOperations.TupleElementNode;
import de.hskempten.tabulang.astNodes.tupleOperations.TupleNode;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.tokenizer.ParameterizedString;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Interpretation interpretation = new Interpretation();
        TextPosition textPosition = new TextPosition(new ParameterizedString(""), 0);

        NumberNode n1 = new NumberNode(new BigInteger("1"), new BigInteger("1"), textPosition);
        NumberNode n2 = new NumberNode(new BigInteger("2"), new BigInteger("1"), textPosition);
        NumberNode n3 = new NumberNode(new BigInteger("3"), new BigInteger("1"), textPosition);
        NumberNode n4 = new NumberNode(new BigInteger("4"), new BigInteger("1"), textPosition);
        NumberNode n11 = new NumberNode(new BigInteger("11"), new BigInteger("1"), textPosition);
        StringNode sHa = new StringNode("ha", textPosition);
        StringNode sLlo = new StringNode("llo", textPosition);
        StringNode s0 = new StringNode("0", textPosition);
        StringNode s1 = new StringNode("1", textPosition);
        StringNode s2 = new StringNode("2", textPosition);
        StringNode sA = new StringNode("a", textPosition);
        IdentifierNode v1 = new IdentifierNode("a", textPosition);
        IdentifierNode v2 = new IdentifierNode("b", textPosition);
        IdentifierNode v3 = new IdentifierNode("c", textPosition);
        IdentifierNode v4 = new IdentifierNode("d", textPosition);
        IdentifierNode v5 = new IdentifierNode("e", textPosition);
        IdentifierNode v6 = new IdentifierNode("x", textPosition);
        AddNode a1 = new AddNode(n1, n2, textPosition);
        AddNode a2 = new AddNode(sHa, sLlo, textPosition);
        SubtractNode sub1 = new SubtractNode(a1, n4, textPosition);
        //System.out.println(sub1.evaluateNode(i));
        //System.out.println(a1.evaluateNode(i));
        //System.out.println(a2.evaluateNode(i));
        AddNode a3 = new AddNode(sHa, sLlo, textPosition);
        //System.out.println(a3.evaluateNode(i));
        MultiplyNode m1 = new MultiplyNode(n1, n2, textPosition);
        NewAssignmentNode as1 = new NewAssignmentNode(v1, n4, textPosition);
        NewAssignmentNode as2 = new NewAssignmentNode(v2, as1, textPosition);
        NewAssignmentNode as3 = new NewAssignmentNode(v3, as2, textPosition);
        as3.evaluateNode(interpretation);
        NewAssignmentNode as4 = new NewAssignmentNode(v4, m1, textPosition);
        //as4.evaluateNode(interpretation);
        GreaterThanNode g1 = new GreaterThanNode(n11, n2, textPosition);
        GreaterThanNode g2 = new GreaterThanNode(n3, n2, textPosition);
        //System.out.println(g1.evaluateNode(i));
        EqualsNode e1 = new EqualsNode(v2, v1, textPosition);
        //System.out.println(e1.evaluateNode(i));
        SpreadNode sp1 = new SpreadNode(n1, n4, textPosition);
        //System.out.println(sp1.evaluateNode(i));

        ArrayList<TermNode> objects1 = new ArrayList<>();
        objects1.add(n3);
        objects1.add(n1);
        objects1.add(n2);
        //ArrayList t1 = new Tuple(objects1);
        TupleNode tupleNode1 = new TupleNode(objects1, textPosition);
        ArrayList<Object> objects2 = new ArrayList<>();
        objects2.add(n3);
        objects2.add(n2);
        objects2.add(n4);
        //ArrayList t2 = new Tuple(objects2);
        TupleNode tupleNode2 = new TupleNode(objects2, textPosition);
        ArrayList<Object> objects3 = new ArrayList<>();
        objects3.add(n1);
        objects3.add(n3);
        objects3.add(n4);
        //ArrayList t3 = new Tuple(objects3);
        TupleNode tupleNode3 = new TupleNode(objects3, textPosition);
        ArrayList<Object> objects4 = new ArrayList<>();
        objects4.add(n1);
        objects4.add(n2);
        objects4.add(n3);
        //ArrayList t4 = new Tuple(objects4);
        TupleNode tupleNode4 = new TupleNode(objects4, textPosition);
        NewAssignmentNode as5 = new NewAssignmentNode(v5, tupleNode1, textPosition);
        //as5.evaluateNode(i);
        //System.out.println(tupleNode2.toString());
        //tupleNode2.evaluateNode(i);
        StringNode s4 = new StringNode("4", textPosition);
        TupleElementNode tupleElementNode1 = new TupleElementNode(tupleNode1, s0, textPosition);
        //System.out.println(tupleElementNode1.evaluateNode(i));
        TupleElementNode tupleElementNode2 = new TupleElementNode(tupleElementNode1, s1, textPosition);
        //System.out.println(tupleElementNode2.evaluateNode(i));
        //System.out.println(tupleNode2.toString());
        CountNode countNode1 = new CountNode(tupleNode2, textPosition);
        //System.out.println(countNode1.evaluateNode(i));
        InTupleNode inTupleNode1 = new InTupleNode(v3, tupleNode1, textPosition);
        //as1.evaluateNode(i);
        //System.out.println(tupleNode1);
        //System.out.println(tupleElementNode1.evaluateNode(i));
        //System.out.println(tupleNode1.evaluateNode(i));

        List<Object> objectsTable1 = new ArrayList<>();
        objectsTable1.add(n2);
        objectsTable1.add(n2);
        objectsTable1.add(n4);
        List<Object> objectsTable2 = new ArrayList<>();
        objectsTable2.add(n2);
        objectsTable2.add(n3);
        objectsTable2.add(n4);
        List<Object> objectsTable3 = new ArrayList<>();
        objectsTable3.add(n2);
        objectsTable3.add(n3);
        objectsTable3.add(n2);
        Tuple t1 = new Tuple(objectsTable1);
        Tuple t2 = new Tuple(objectsTable2);
        Tuple t3 = new Tuple(objectsTable3);
        Table table1 = new Table(t1, t2);
        //System.out.println(tableNode1.evaluateNode(interpretation));


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
        NewAssignmentNode a5 = new NewAssignmentNode(v6, n4, textPosition);
        //a5.evaluateNode(interpretation);
        GreaterThanNode g3 = new GreaterThanNode(v6, new NumberNode(new BigInteger("6"), new BigInteger("1"), textPosition), textPosition);
        ExistsSuchThatNode existsSuchThatNode1 = new ExistsSuchThatNode(tupleNode1, g3, "x", textPosition);
        ForAllSuchThatNode forAllSuchThatNode1 = new ForAllSuchThatNode(tupleNode1, g3, "x", textPosition);
        //System.out.println(tupleNode1);
        //System.out.println(g3);
        //System.out.println("ExistsSuchThat: " + existsSuchThatNode1.evaluateNode(interpretation));
        //System.out.println("ForAllSuchThat: " + forAllSuchThatNode1.evaluateNode(interpretation));

        String param1 = "param1";
        IdentifierNode identifierNode1 = new IdentifierNode(param1, textPosition);
        ArrayList<IdentifierNode> parameters = new ArrayList<>();
        parameters.add(identifierNode1);
        ArrayList<StatementNode> statements = new ArrayList<>();
        AddNode fAdd1 = new AddNode(identifierNode1, n11, textPosition);
        NewAssignmentNode fAssignment1 = new NewAssignmentNode(identifierNode1, fAdd1, textPosition);
        AddNode fAdd2 = new AddNode(identifierNode1, n3, textPosition);
        AssignmentNode fAssignment2 = new AssignmentNode(identifierNode1, fAdd2, textPosition);
        ReturnNode returnNode1 = new ReturnNode(identifierNode1, textPosition);
        ReturnNode returnNode2 = new ReturnNode(identifierNode1, textPosition);
        statements.add(fAssignment1);
        statements.add(returnNode1);
        statements.add(fAssignment2);
        //statements.add(returnNode1);

        FunctionDeclarationNode functionDeclarationNode1 = new FunctionDeclarationNode(parameters, statements, textPosition);
        //System.out.println(functionDeclarationNode1.evaluateNode(interpretation));
        String idFunction1 = "myFirstFunction";
        IdentifierNode idNode1 = new IdentifierNode(idFunction1, textPosition);
        NewAssignmentNode fAssignment3 = new NewAssignmentNode(idNode1, functionDeclarationNode1, textPosition);
        fAssignment3.evaluateNode(interpretation);

        /*System.out.println(tupleNode2.toString());
        tupleNode2.evaluateNode(i);
        System.out.println(tupleNode2.toString());*/

        SetNode setNode1 = new SetNode(v1, 1, textPosition);
        setNode1.evaluateNode(interpretation);

        GreaterThanNode greaterThanNode2 = new GreaterThanNode(v1, n3, textPosition);
        //greaterThanNode2.evaluateNode(interpretation);

        AssignmentNode a10 = new AssignmentNode(v6, v1, textPosition);
        //a10.evaluateNode(interpretation);

        ArrayList<TermNode> funcParameter = new ArrayList<>();
        funcParameter.add(n11);
        FunctionCallNode functionCallNode1 = new FunctionCallNode(idNode1, funcParameter, textPosition);
        //functionCallNode1.evaluateNode(interpretation);
        NewAssignmentNode assignmentNodeFunction = new NewAssignmentNode(v6, functionCallNode1, textPosition);
        assignmentNodeFunction.evaluateNode(interpretation);

        System.out.println("-.-.-.-.-.-.-.-");
        ArrayList<TermNode> funcParameter2 = new ArrayList<>();
        funcParameter2.add(n1);
        FunctionCallNode functionCallNode2 = new FunctionCallNode(idNode1, funcParameter2, textPosition);
        //functionCallNode2.evaluateNode(interpretation);


        AddNode addNode3 = new AddNode(v1, n1, textPosition);
        NewAssignmentNode assignmentNode3 = new NewAssignmentNode(v2, addNode3, textPosition);
        //AssignmentNode assignmentNode4 = new AssignmentNode(v1, assignmentNode3);
        //assignmentNode3.evaluateNode(interpretation);
        ArrayList<Object> blockArray = new ArrayList<>();
        blockArray.add(assignmentNode3);
        //blockArray.add(assignmentNode4);
        //BlockNode blockNode1 = new BlockNode(blockArray);
        //blockNode1.evaluateNode(interpretation);

        System.out.println(tupleElementNode1);
        System.out.println(tupleElementNode1.evaluateNode(interpretation));

        System.out.println(sp1.evaluateNode(interpretation));



        System.out.println();
        System.out.println(".......................");
        System.out.println("Outer Environment: ");
        Iterator it = interpretation.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

    }
}
