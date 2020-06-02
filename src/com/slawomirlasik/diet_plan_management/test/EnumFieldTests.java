package com.slawomirlasik.diet_plan_management.test;

public class EnumFieldTests {

    enum something {
        ONE, TWO, THREE
    }

    public static void main(String[] args) {
        something flag = something.ONE;

        System.out.println(flag.name().contains("ON"));
    }
}
