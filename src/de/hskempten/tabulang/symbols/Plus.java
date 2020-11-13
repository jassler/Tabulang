package de.hskempten.tabulang.symbols;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.TerminalItem;

public class Plus implements TerminalItem {
    private String plus = "+";

    public Plus() {
    }

    @Override
    public void addToStack(Interpretation i) {
        i.getOperatorStack().push(plus);
    }
}
