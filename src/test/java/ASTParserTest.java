import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.items.ast.ASTProgramParser;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.nodes.*;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASTParserTest {
    private Lexer l;
    private TabulangParser parser;

    private final NumberAST number1 = new NumberAST(new NumberItem(BigInteger.valueOf(1)));
    private final NumberAST number2 = new NumberAST(new NumberItem(BigInteger.valueOf(2)));
    private final NumberAST number3 = new NumberAST(new NumberItem(BigInteger.valueOf(3)));

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
        statements.add(new AssignmentAST("a", number1));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier()
        );
        assertEquals(
                ((NumberAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getNumber().getMyNumber(),
                ((NumberAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getNumber().getMyNumber()
        );
    }

    @Test
    void assignAdd() throws Exception {
        l.setText("a := 1 + 2;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        AddAST addition = new AddAST(number1, number2);
        statements.add(new AssignmentAST("a", addition));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getNumber().getMyNumber(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getNumber().getMyNumber(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getNumber().getMyNumber()
        );
    }

    @Test
    void assignAddMul() throws Exception {
        l.setText("a := 1 + 2 * 3;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        MultiplyAST multiplication = new MultiplyAST(number2, number3);
        AddAST addition = new AddAST(number1, multiplication);
        statements.add(new AssignmentAST("a", addition));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getNumber().getMyNumber(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((MultiplyAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getLeft()).getNumber().getMyNumber(),
                ((NumberAST) ((MultiplyAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getLeft()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((MultiplyAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getRight()).getNumber().getMyNumber(),
                ((NumberAST) ((MultiplyAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getRight()).getNumber().getMyNumber()
        );
    }@Test
    void assignAddAdd() throws Exception {
        l.setText("a := 1 + 2 + 3;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        AddAST additionI = new AddAST(number1, number2);
        AddAST addition = new AddAST(additionI, number3);
        statements.add(new AssignmentAST("a", addition));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getLeft()).getNumber().getMyNumber(),
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getLeft()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getRight()).getNumber().getMyNumber(),
                ((NumberAST) ((AddAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getRight()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((AddAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getNumber().getMyNumber(),
                ((NumberAST) ((AddAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getNumber().getMyNumber()
        );
    }

    @Test
    void assignPowPow() throws Exception {
        l.setText("a := 1 ^ 2 ^ 3;");

        ArrayList<StatementAST> statements = new ArrayList<StatementAST>();
        PowerAST powerI = new PowerAST(number2, number3);
        PowerAST power = new PowerAST(number1, powerI);
        statements.add(new AssignmentAST("a", power));
        ProgramAST exp = new ProgramAST(statements);

        ProgramItem actPrg = parser.parseN();
        ProgramAST act = ASTProgramParser.instance.parse(actPrg);

        //exp.print(0);
        //act.print(0);

        assertEquals(exp.getStatements().size(), act.getStatements().size());
        assertEquals(
                ((AssignmentAST) exp.getStatements().get(0)).getIdentifier(),
                ((AssignmentAST) act.getStatements().get(0)).getIdentifier()
        );
        assertEquals(
                ((NumberAST) ((PowerAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getLeft()).getNumber().getMyNumber(),
                ((NumberAST) ((PowerAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getLeft()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getLeft()).getNumber().getMyNumber(),
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getLeft()).getNumber().getMyNumber()
        );
        assertEquals(
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) exp.getStatements().get(0)).getTerm()).getRight()).getRight()).getNumber().getMyNumber(),
                ((NumberAST) ((PowerAST) ((PowerAST) ((AssignmentAST) act.getStatements().get(0)).getTerm()).getRight()).getRight()).getNumber().getMyNumber()
        );
    }
}
