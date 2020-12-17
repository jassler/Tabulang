package de.hskempten.tabulang.standardBibliothek;

public class FunctionInterface {
    Class[] _argTypes;
    InternalFunction _internalFunction;

    public FunctionInterface(InternalFunction internalFunction, Class... classes){
        _internalFunction = internalFunction;
        _argTypes = classes;
    }


// EXCEPTION NEU BAUEN - eigene klasse
    // VERERBUNG

    public Object execute(Object... objs) {
        for(int i = 0; i < objs.length; i++) {
            if(!(objs[i].getClass().isAssignableFrom(_argTypes[i]))){
                throw new ClassCastException("Classtype not equals");
            }
        }
        return _internalFunction.compute(objs);
    }
}
