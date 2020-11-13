package de.hskempten.tabulang.symbols;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.terminal.TerminalItem;
import de.hskempten.tabulang.helper.CreateArithmeticItem;

public class CloseParenthesis implements TerminalItem {
    private String closeP = ")";
    @Override
    public void addToStack(Interpretation i) {
        //System.out.println(closeP + " gefunden");
        while(i.getOperatorStack().peek() != "("){
            //System.out.println("....nutze daher nun: " + i.getOperatorStack().peek());
            CreateArithmeticItem.getInstance().createCorrespondingItem(i);
        }
        if(i.getOperatorStack().peek() == "("){
            //System.out.println("....und entferne: " + i.getOperatorStack().peek());
            i.getOperatorStack().pop();
        }
    }
}
