package com.test;

public class TestDemo {

    public static void main(String[] args) {
        getName("2");
    }
    public static <T> T getName(T t){
        System.out.println(t.toString());
        return t;
    }
}
