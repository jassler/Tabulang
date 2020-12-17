package de.hskempten.tabulang.standardBibliothek;

public class MainClass {
    public static void main(String[] args){
        System.out.println(isString("Ausgabe aus der main()-Methode"));
        System.out.println(isString(0));
    }
    public static boolean isString(Object item){
        return item.getClass().equals(String.class);
    }
}
