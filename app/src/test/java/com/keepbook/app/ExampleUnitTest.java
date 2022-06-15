package com.keepbook.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


 class A {

    private String nameA="A";

    public void getName() {
        System.out.println("父类"+nameA);
    }
    public static void main(String[] args) {
    }

}


 class B extends A{
    private String nameB="B";

    @Override
    public void getName() {
        System.out.println("子类"+nameB);
        super.getName();
    }
    @Test
    public  void ccccc(String[] args) {
        assertEquals(4, 2 + 2);
        B b=new B();
        b.getName();

    }
}

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        System.out.printf("分为");
        B b=new B();
        b.getName();
    }
}