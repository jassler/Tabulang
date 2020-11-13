package de.hskempten.tabulang.arithmetic;


import de.hskempten.tabulang.exampleTest.Interpretable;
import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.items.OrdinalItem;
import de.hskempten.tabulang.items.TermRItem;

import java.math.BigDecimal;

import static java.lang.Float.parseFloat;

public class AddItem implements Interpretable {
    private Object left;
    private Object right;

    public AddItem(Object right, Object left) {
        this.left = left;
        this.right = right;
    }

    public AddItem() {
    }

    public Object getLeft() {
        return left;
    }

    public void setLeft(OrdinalItem left) {
        this.left = left;
    }

    public Object getRight() {
        return right;
    }

    public void setRight(TermRItem right) {
        this.right = right;
    }

    @Override
    public void interpret(Interpretation i) {
        String secondOp = i.getTypeStack().pop();
        String firstOp = i.getTypeStack().pop();
        Number secondNumber;
        Number firstNumber;
        //TODO evtl zusätzliches if für Fall != String, Number, Identifier, (Tupel)
        //TODO evtl auskapseln und in CreateArithmeticItem.createCorrespondingItem packen mit komplettem if Zweig alles Möglichkeiten
        if(firstOp == "String" || secondOp == "String"){
            String secondString = i.getOperandStack().pop();
            String firstString = i.getOperandStack().pop();
            i.getOperandStack().push(firstString + secondString);
            i.getTypeStack().push("String");
        } else {
            if(secondOp == "Identifier"){
                secondNumber = parseFloat(i.getEnvironment().get(i.getOperandStack().pop()).toString());
            } else {
                secondNumber = parseFloat(i.getOperandStack().pop());
            }
            if(firstOp == "Identifier"){
                firstNumber = parseFloat(i.getEnvironment().get(i.getOperandStack().pop()).toString());
            } else {
                firstNumber = parseFloat(i.getOperandStack().pop());
            }
            BigDecimal sum = new BigDecimal(firstNumber.floatValue()).add(new BigDecimal(secondNumber.floatValue()));
            System.out.println("Addition beider Zahlen: " + sum);
            i.getOperandStack().push(sum.toString());
            i.getTypeStack().push("Number");

        }
    }
}
