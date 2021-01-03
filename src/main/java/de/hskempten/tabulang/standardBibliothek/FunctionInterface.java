package de.hskempten.tabulang.standardBibliothek;

public class FunctionInterface {
    Class[] _argTypes;
    InternalFunction _internalFunction;

    public FunctionInterface(InternalFunction internalFunction, Class... classes){
        _internalFunction = internalFunction;
        _argTypes = classes;
    }

    // VERERBUNG

    public Object execute(Object... objs) {
        /*for(int i = 0; i < objs.length; i++) {
            if(!(objs[i].getClass().isInstance(_argTypes[i]))){
                throw new ClassNotEqual("Classtype not equals");
            }
        }*/
        return _internalFunction.compute(objs);
    }
}
