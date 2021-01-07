package de.hskempten.tabulang.standardLibrary;

import java.util.ArrayList;

public class ToLowerCase implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(args.length > 1){
            throw new IndexOutOfBoundsException("Max items: 1");
        }
        var returnList = new ArrayList<String>(((ArrayList)args[0]).size());
        var item = args[0];
        for(var strItem : (ArrayList)item){
            if(strItem.getClass().equals(String.class)){
                returnList.add(((String) strItem).toLowerCase());
            }
        }
        return returnList;
    }
}
