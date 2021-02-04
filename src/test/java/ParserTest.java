import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


        Interpretation interpretation = new Interpretation();

        parser = new TabulangParser(l, interpretation);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyParse() throws ParseTimeException {
        l.setText("");
        ArrayList<StatementItem> statements = new ArrayList<>();
        ProgramItem prg = new ProgramItem(statements);

        assertEquals(parser.parse().getMyStatements(), prg.getMyStatements());
    }

    @Test
    void assign() throws ParseTimeException {
        l.setText("a := 9;");
        ArrayList<StatementItem> statements = new ArrayList<>();
        statements.add(new StatementItem(new VarDefItem(new IdentifierItem("a"), new TermItem(new TermRItem(), new OrdinalItem(new NumberItem("9"))), true)));
        ProgramItem prg = new ProgramItem(statements);

        ProgramItem act = parser.parse();
        assertEquals(prg.getMyStatements().size(), act.getMyStatements().size());
        assertEquals(
                prg.getMyStatements().get(0).getMyVarDef().getMyIdentifier().getMyString(),
                act.getMyStatements().get(0).getMyVarDef().getMyIdentifier().getMyString()
        );
        assertEquals(
                prg.getMyStatements().get(0).getMyVarDef().getMyTerm().getMyOrdinal().getMyNumber().getNumerator(),
                act.getMyStatements().get(0).getMyVarDef().getMyTerm().getMyOrdinal().getMyNumber().getNumerator()
        );
    }
}
