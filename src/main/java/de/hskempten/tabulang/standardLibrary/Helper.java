package de.hskempten.tabulang.standardLibrary;

public class Helper {
    public static boolean LengthReviewer(int maxLength, Object... args){
        if(args.length > maxLength){
            throw new IndexOutOfBoundsException(String.format("Max items: %s", maxLength));
        }
        return true;
    }

    public static String FindPath(Object item){
        if(((String) item).contains("/") || ((String) item).contains("\\")){
            return (String) item;
        }
        return null;
    }

    public static String FindSqlStatement(Object item){
        if(((String) item).contains("SELECT")){
            return (String) item;
        }
        return null;
    }

    public static boolean hasExpectedObjectType(Class classes, Object item) {
        return item.getClass().equals(classes);
    }
}
