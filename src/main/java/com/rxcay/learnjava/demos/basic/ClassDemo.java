package com.rxcay.learnjava.demos.basic;

import com.rxcay.learnjava.demos.model.MyBase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rx_w@outlook.com
 * @version 1.0
 * @date 11/5/21 8:33 下午
 * @description
 */
public class ClassDemo {
    public static void test() {
        A.foo();
        Child son = new Child();
        son.bar();
        ((Parent)son).zoo();

        MyBase b = new MyBase();
//        MyBase.privateStaticInt = 0;
//        MyBase.packagePrivateStaticInt = 0; // no access
//        MyBase.protectedStaticInt = 0;
        MyBase.publicStaticInt = 0;

//        b.privateInt = 0;
//        b.packagePrivateInt = 0; // no access
//        b.protectedInt = 0;
        b.publicInt = 0;

//        MyBase.privateStaticM();
//        MyBase.protectedStaticM(); // no access
//        MyBase.packagePrivateStaticM();
        MyBase.publicStaticM();

//        b.privateM();
//        b.protectedM(); // no access
//        b.packagePrivateM();
        b.publicM();
    }

}

interface A {
    int a = 0;// public static final: interface variables are public static and final .It cannot have state.
    private static void secret() {
        System.out.println("A's private static secret");
    }
    static void foo(){ // auto public
        //a = 1; // impossible
        System.out.println("A static foo");
        secret();

    }


    default void bar() {
        this.hoo(); // hoo() the same.
        System.out.println("default A bar");

    }
    private void hoo(){ // only private method can have body
        System.out.println("interface A private method hoo");
    }
}

interface B extends A {
    default void bar() {
        A.super.bar(); // in this way, access the super interface default method
        System.out.println("default B bar");
    }
}

interface C {
    default void bar() {
        System.out.println("default C bar");
    }
}


abstract class Parent {
    static void baz(){
        System.out.println("parent static baz");
    }
    abstract protected void zoo();
}
class Child extends Parent implements B,C  {
    static void baz(){
        System.out.println("child static baz");
    }
    @Override
    public void bar(){ // diamond extension, must override this way to avoid
        System.out.println("override Child bar");
        B.super.bar();
        C.super.bar();
        baz(); // static method in this class;
        // if there is no baz() in this class, then the parent class will be invoked.
        Parent.baz(); // Parent static baz()
        // There is no way to conduct override with static method in class. This is not OOP.
    }

    @Override
    protected void zoo() {
        System.out.println("zoo in Child");
    }
}

class MyExt extends MyBase {

    public static void foo() {

//        privateStaticInt = 0; //private or package-private
//        packagePrivateStaticInt = 0;
        protectedStaticInt = 0;
        publicStaticInt = 0;

//        privateInt = 0;
//        packagePrivateInt = 0; // non static
//        protectedInt = 0;
//        publicInt = 0;

//        privateStaticM();
//        packagePrivateStaticM(); //private or package-private
        protectedStaticM();
        publicStaticM();

//        privateM();
//        packagePrivateM(); // nonstatic
//        protectedM();
//        publicM();

    }
    public void bar() { // all access
//        privateStaticInt = 0; //private or package-private
//        packagePrivateStaticInt = 0;
        protectedStaticInt = 0;
        publicStaticInt = 0;

//        privateInt = 0;
//        packagePrivateInt = 0;
        protectedInt = 0;
        publicInt = 0;

//        privateStaticM();
//        packagePrivateStaticM(); //private or package-private
        protectedStaticM();
        publicStaticM();

//        privateM();
//        packagePrivateM();
        protectedM();
        publicM();
    }
}

