package de.hskempten.tabulang.standardLibrary;

import java.util.ArrayList;
import java.util.Locale;

public class ToUpperCase implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        if(args.length != 1){
            throw new IndexOutOfBoundsException("Need 1 item!");
        }
        if(args[0] instanceof String s)
            return s.toUpperCase(Locale.ROOT);

        var returnList = new ArrayList<String>(((ArrayList)args[0]).size());
        var item = args[0];
        for(var strItem : (ArrayList)item){
            if(strItem.getClass().equals(String.class)){
                returnList.add(((String) strItem).toUpperCase());
            }
        }
        return returnList;
    }
}
