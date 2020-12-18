import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.nodes.*;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASTParserTest {
    private Lexer l;
    private TabulangParser parser;

    private final NumberNode number1 = new NumberNode(1);
    private final NumberNode number2 = new NumberNode(2);
    private final NumberNode number3 = new NumberNode(3);

    @BeforeEach
    void setUp() {
        l = new Lexer();

        for (var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }
        l.addOneLineCommentMarker("//");


        Interpretation interpretation = new Interpretation();

        parser = new TabulangParser(l, interpretation);
    }

    @Test
    void assignSimple() throws Exception {
        l.setText("a := 1;");

        ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
        statements.add(new NewAssignmentNode(new IdentifierNode("a"), number1));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((IdentifierNode)((AssignmentNode) exp.getStatements().get(0)).getLeftNode()).getIdentifier(),
                ((IdentifierNode)((AssignmentNode) act.getStatements().get(0)).getLeftNode()).getIdentifier()
        );
        assertEquals(
                ((NumberNode) ((AssignmentNode) exp.getStatements().get(0)).getRightNode()).getFloatValue(),
                ((NumberNode) ((AssignmentNode) act.getStatements().get(0)).getRightNode()).getFloatValue()
        );
    }

    /*@Test
    void assignAdd() throws Exception {
        l.setText("a := 1 + 2;");

        ArrayList<StatementNode> statements = new ArrayList<StatementNode>();
        AddNode addition = new AddNode(number1, number2);
        statements.add(new NewAssignmentNode(new IdentifierNode("a"), addition));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentNode) exp.getStatements().get(0)).getIdentifier().getString(),
                ((AssignmentNode) act.getStatements().get(0)).getIdentifier().getString()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getFloatValue(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getFloatValue()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getFloatValue(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getFloatValue()
        );
    }

    @Test
    void assignAddMul() throws Exception {
        l.setText("a := 1 + 2 * 3;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        MultiplyAST multiplication = new MultiplyAST(number2, number3);
        AddAST addition = new AddAST(number1, multiplication);
        statements.add(new AssignmentAST(new IdentifierAST("a"), addition, true));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier().getString(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier().getString()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getFloatValue(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getFloatValue()
        );
        assertEquals(
                ((NumberAST) ((MultiplyAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getLeft()).getFloatValue(),
                ((NumberAST) ((MultiplyAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getLeft()).getFloatValue()
        );
    }

    @Test
    void assignAddAdd() throws Exception {
        l.setText("a := 1 + 2 + 3;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        AddAST additionI = new AddAST(number1, number2);
        AddAST addition = new AddAST(additionI, number3);
        statements.add(new AssignmentAST(new IdentifierAST("a"), addition, true));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier().getString(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier().getString()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getLeft()).getFloatValue(),
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getLeft()).getFloatValue()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getRight()).getFloatValue(),
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getRight()).getFloatValue()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getFloatValue(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getFloatValue()
        );
    }

    @Test
    void assignPowPow() throws Exception {
        l.setText("a := 1 ^ 2 ^ 3;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        PowerAST powerI = new PowerAST(number2, number3);
        PowerAST power = new PowerAST(number1, powerI);
        statements.add(new AssignmentAST(new IdentifierAST("a"), power, true));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier().getString(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier().getString()
        );
        assertEquals(
                ((NumberAST) ((PowerAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getFloatValue(),
                ((NumberAST) ((PowerAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getFloatValue()
        );
        assertEquals(
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getLeft()).getFloatValue(),
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getLeft()).getFloatValue()
        );
        assertEquals(
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getRight()).getFloatValue(),
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getRight()).getFloatValue()
        );
    }*/
}
