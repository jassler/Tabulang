import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
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

    private final NumberAST number1 = new NumberAST(1);
    private final NumberAST number2 = new NumberAST(2);
    private final NumberAST number3 = new NumberAST(3);

    @BeforeEach
    void setUp() {
        l = new Lexer();

        for (var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }
        l.addOneLineCommentMarker("//");


        Interpreter i = new Interpreter();

        parser = new TabulangParser(l, i);
    }

    @Test
    void assignSimple() throws Exception {
        l.setText("a := 1;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        statements.add(new AssignmentAST(new IdentifierAST("a"), number1));
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
                ((NumberAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getFloatValue(),
                ((NumberAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getFloatValue()
        );
    }

    @Test
    void assignAdd() throws Exception {
        l.setText("a := 1 + 2;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        AddAST addition = new AddAST(number1, number2);
        statements.add(new AssignmentAST(new IdentifierAST("a"), addition));
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
        statements.add(new AssignmentAST(new IdentifierAST("a"), addition));
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
        statements.add(new AssignmentAST(new IdentifierAST("a"), addition));
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
        statements.add(new AssignmentAST(new IdentifierAST("a"), power));
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
    }
}
