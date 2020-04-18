package com.slawomirlasik.diet_plan_management.test;

import com.slawomirlasik.diet_plan_management.mp1.Mp1;

public class TestingClass {

    public static void main(String[] args) {
        for(int i = 1 ; i < 10000; i++){
            System.out.println(i + " : " + Mp1.getYearStringPolish(i));
        }
    }
}
