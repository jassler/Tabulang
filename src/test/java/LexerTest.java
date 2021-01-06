import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {
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
    void keywordLexer() throws ParseTimeException {
        //Keywords which start with an other keyword must be placed before the shorter one
        l.setText("after as average background before count direction distinct else exists false filter forAll font " +
                "fontStyle " +
                "for " +
                "foreground " +
                "from function group hiding horizontal " +
                "if in " +
                "intersect " +
                "mark " +
                "not null return set size suchThat true unite using vertical " +
                "");
        l.lookahead();
        while (!l.isDone()){
            System.out.println("Type: "+ l.lookahead().getType() + " Content: "+ l.lookahead().getContent());
            assertEquals("keyword",l.lookahead().getType());
            l.getNextToken();
        }

    }

}
