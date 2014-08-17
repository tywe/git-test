package org.teng.java.study.basic;

import java.util.Arrays;

public class BasicStudy {
	public static void instanceofTest() {

        Parent obj1 = new Parent();
        Parent obj2 = new Child();

        System.out.println("obj1 instanceof Parent: "
            + (obj1 instanceof Parent));
        System.out.println("obj1 instanceof Child: "
            + (obj1 instanceof Child));
        System.out.println("obj1 instanceof MyInterface: "
            + (obj1 instanceof MyInterface));
        System.out.println("obj2 instanceof Parent: "
            + (obj2 instanceof Parent));
        System.out.println("obj2 instanceof Child: "
            + (obj2 instanceof Child));
        System.out.println("obj2 instanceof MyInterface: "
            + (obj2 instanceof MyInterface));
    }
	
	public static void arrayMethodsTest(){
		char[] copyFrom = { 'd', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a',
				't', 'e', 'd' };
		char[] copyTo = new char[7];

		System.arraycopy(copyFrom, 2, copyTo, 0, 7);
		System.out.println(new String(copyTo));
		//
		copyTo = Arrays.copyOfRange(copyFrom, 0, 2);
		System.out.println("copy:" + String.valueOf(copyTo));
		
		Arrays.sort(copyTo);
		System.out.println("sort:" + String.valueOf(copyTo));
		
		int index = Arrays.binarySearch(copyFrom, 'a');//(copyFrom, 0, 2);
		System.out.println("search:" + String.valueOf(copyTo) + ", index:" + index);
		
		Arrays.fill(copyTo, 'a');//(copyFrom, 0, 2);
		System.out.println("fill:" + String.valueOf(copyTo));
		
		System.out.println("equal(copyFrom, copyTo):" + Arrays.equals(copyFrom, copyTo));
		
		int a = 5;
		System.out.println(a++/2);
		System.out.println(-++a/2);
	}
	
	
	public static void main(String[] args) {
		//arrayMethodsTest();
		instanceofTest();
	}	
}


class Parent {}
class Child extends Parent implements MyInterface {}
interface MyInterface {}
