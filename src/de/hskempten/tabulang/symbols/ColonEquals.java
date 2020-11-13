package de.hskempten.tabulang.symbols;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.TerminalItem;

public class ColonEquals implements TerminalItem {
    private String colonEquals = ":=";
    @Override
    public void addToStack(Interpretation i) {
        i.getOperatorStack().push(colonEquals);
        System.out.println("Schreibe in OperatorStack: " + colonEquals);
    }
}
