import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Lexer l;
    private TabulangParser parser;

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

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyParse() throws ParseTimeException {
        l.setText("");
        ArrayList<StatementItem> statements = new ArrayList<>();
        ProgramItem prg = new ProgramItem(statements);

        assertEquals(parser.parseN().getMyStatements(), prg.getMyStatements());
    }

    @Test
    void assign() throws ParseTimeException {
        l.setText("a := 9;");
        ArrayList<StatementItem> statements = new ArrayList<>();
        statements.add(new StatementItem(new VarDefItem(new IdentifierItem("a"), new TermItem(new TermRItem(), new OrdinalItem(new NumberItem(BigInteger.valueOf(9)))))));
        ProgramItem prg = new ProgramItem(statements);

        ProgramItem act = parser.parseN();
        assertEquals(prg.getMyStatements().size(), act.getMyStatements().size());

        Assignment asAct = (Assignment)((VarDefItem)act.getMyStatements().get(0).getStatement()).getAssignmentOrProceduralF();
        Assignment asPrg = (Assignment)((VarDefItem)prg.getMyStatements().get(0).getStatement()).getAssignmentOrProceduralF();

        assertEquals(
                asPrg.getIdentifier().getMyString(),
                asAct.getIdentifier().getMyString()
        );
        assertEquals(
                asPrg.getTerm().getMyOrdinal().getMyNumber().getMyNumber(),
                asAct.getTerm().getMyOrdinal().getMyNumber().getMyNumber()
        );
    }
}
