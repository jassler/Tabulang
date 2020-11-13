package de.hskempten.tabulang.arithmetic;

import de.hskempten.tabulang.exampleTest.Interpretation;

import java.math.BigDecimal;

import static java.lang.Float.parseFloat;

public class PowItem implements ArithmeticIF{
    @Override
    public void evaluate(Interpretation i) {
        String secondOp = i.getTypeStack().pop();
        String firstOp = i.getTypeStack().pop();
        Number secondNumber;
        Number firstNumber;
        //TODO Fehler wenn != Identifier oder != Number
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
        BigDecimal sum = new BigDecimal(firstNumber.floatValue()).pow(new BigDecimal(secondNumber.intValue()).intValue());
        System.out.println("To the power of Summe: " + sum);
        i.getOperandStack().push(sum.toString());
        i.getTypeStack().push("Number");
    }
}
