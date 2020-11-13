package de.hskempten.tabulang.exampleTest.terminal;


import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.LangItem;

public interface TerminalItem extends LangItem {

    public void addToStack(Interpretation i);
}
