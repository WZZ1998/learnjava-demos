package com.rxcay.learnjava.demos.model;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/6/21 3:42 上午
 * @description
 */
public class MyBase {
    private static int privateStaticInt = 1;
    static int packagePrivateStaticInt = 2;
    protected static int protectedStaticInt = 3;
    public static int publicStaticInt = 4;
    private int privateInt = 1;
    int packagePrivateInt = 2;
    protected int protectedInt = 3;
    public int publicInt = 4;
    private static void privateStaticM(){

    }
    protected static void protectedStaticM() {

    }
    static void packagePrivateStaticM(){

    }
    public static void publicStaticM(){

    }
    private void privateM(){

    }
    protected void protectedM() {

    }
    void packagePrivateM(){

    }
    public void publicM(){

    }

    public static void foo() {

        privateStaticInt = 0;
        packagePrivateStaticInt = 0;
        protectedStaticInt = 0;
        publicStaticInt = 0;

//        privateInt = 0;
//        packagePrivateInt = 0; // non static
//        protectedInt = 0;
//        publicInt = 0;

        privateStaticM();
        protectedStaticM();
        packagePrivateStaticM();
        publicStaticM();

//        privateM();
//        protectedM();
//        packagePrivateM(); // nonstatic
//        publicM();

    }
    public void bar() { // all access
        privateStaticInt = 0;
        packagePrivateStaticInt = 0;
        protectedStaticInt = 0;
        publicStaticInt = 0;

        privateInt = 0;
        packagePrivateInt = 0;
        protectedInt = 0;
        publicInt = 0;

        privateStaticM();
        protectedStaticM();
        packagePrivateStaticM();
        publicStaticM();

        privateM();
        protectedM();
        packagePrivateM();
        publicM();
    }

}
