package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.OrdinalAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class TupelAST implements OrdinalAST {
    ArrayList<TermAST> tList;

    public TupelAST() {
        this.tList = new ArrayList<TermAST>();
    }

    public TupelAST(TermAST oneTerm) {
        this.tList = new ArrayList<TermAST>();
        this.tList.add(oneTerm);
    }

    public TupelAST(ArrayList<TermAST> tList) {
        this.tList = tList;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        for (int i = 0; i < tList.size(); i++) {
            tList.get(i).print(offset + this.getClass().getSimpleName().length() + 1);
        }
    }
}
