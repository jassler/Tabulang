package de.hskempten.tabulang.standardBibliothek;

import java.util.ArrayList;

public class ToUpperCase implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        var returnList = new ArrayList<>();
        for(var item : args){
            if(item.getClass().equals(String.class)){
                returnList.add(((String) item).toUpperCase());
            }
        }
        return returnList;
    }
}
