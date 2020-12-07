package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class FilterAST implements TermAST {
    private ArrayList<PredAST> preds;

    public FilterAST(ArrayList<PredAST> preds) {
        this.setPreds(preds);
    }

    public ArrayList<PredAST> getPreds() {
        return preds;
    }

    public void setPreds(ArrayList<PredAST> preds) {
        this.preds = preds;
    }


    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Preds: ");
        for (int i = 0; i < this.getPreds().size(); i++) {
            this.getPreds().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Preds: ").length())).length());
        }
    }
}
