package jpabook.jpashop;

import jpabook.jpashop.domain.Address;

public class ValueMain {

    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("a==b : " + (a==b));

        Address addressA = new Address("서울시", "영성동로", "12345");
        Address addressB = new Address("서울시", "영성동로", "12345");
        System.out.println("addressA==addressB : " + (addressA==addressB));
        System.out.println("addressA.equals(addressB) = " + addressA.equals(addressB));


    }
}
