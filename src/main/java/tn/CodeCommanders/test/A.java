package tn.CodeCommanders.test;

public class A {
    private static A instance ;
    private A(){}

    public static A getInstance() {
        if (instance == null)
            instance = new A();


        return null;
    }
}

