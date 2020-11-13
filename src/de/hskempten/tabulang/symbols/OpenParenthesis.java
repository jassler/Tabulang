package de.hskempten.tabulang.symbols;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.TerminalItem;

public class OpenParenthesis implements TerminalItem {
    private String openP = "(";

    @Override
    public void addToStack(Interpretation i) {
        i.getOperatorStack().push(openP);
        System.out.println("Schreibe in OperatorStack: " + openP);
    }
}
