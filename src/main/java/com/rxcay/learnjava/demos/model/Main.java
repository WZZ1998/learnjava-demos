package com.rxcay.learnjava.demos.model;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/6/21 3:48 上午
 * @description
 */
public class Main {
    public static void main(String[] args) {
        MyBase b = new MyBase();

        // MyBase.privateStaticInt = 0; private access
        MyBase.packagePrivateStaticInt = 0;
        MyBase.protectedStaticInt = 0;
        MyBase.publicStaticInt = 0;

        // b.privateInt = 0; private
        b.packagePrivateInt = 0;
        b.protectedInt = 0;
        b.publicInt = 0;

        //MyBase.privateStaticM(); private
        MyBase.protectedStaticM();
        MyBase.packagePrivateStaticM();
        MyBase.publicStaticM();

        // b.privateM(); private
        b.protectedM();
        b.packagePrivateM();
        b.publicM();
    }
}
