package org.teng.java.study.basic;

import java.util.Arrays;
class BasicStudy {
	
	
	
	/**
	 * instanceof类型判断测试
	 * 父对象instanceof子类：false
	 * 子对象instanceof父类：true
	 */
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
	
	/**
	 * Arrays类提供了array操作的排序、查找、填充、复制等功能
	 */
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
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//arrayMethodsTest();
		//instanceofTest();
		/*测试块执行顺序*/
		ExcuteOrder eo = new ExcuteOrder();
		eo.continueTest();
		eo.breakTest();
	}	
}

//测试用到的类

class Parent {}
class Child extends Parent implements MyInterface {}
interface MyInterface {}
/**
 * 测试一个类的执行顺序
 * @author Administrator
 *
 */
class ExcuteOrder{
	/**
	 * 测试构造函数和块的执行顺序
	 * 在类中，块的执行要比构造早##
	 */
	static{
		System.out.println("this is a static block_1");
	}
	
	{
		System.out.println("this is a non-static block_1");
	}
	
	static{
		System.out.println("this is a static block_2");
	}
	
	public ExcuteOrder(){
		System.out.println("this is construct function");
		{
			System.out.println("....");
		}
	}
	
	{
		System.out.println("this is a non-static block_2");
	}
	
	static{
		System.out.println("this is a static block_3");
	}
	
	/**
	 * continue的特殊用法：双层循环跳出外层
	 */
	public void continueTest(){
		System.out.println("=========================特殊的continue测试=========================");
		 String searchMe = "Look for a substring in me";
	        String substring = "sub";
	        boolean foundIt = false;

	        int max = searchMe.length() - 
	                  substring.length();

	    test:
	        for (int i = 0; i <= max; i++) {
	            int n = substring.length();
	            int j = i;
	            int k = 0;
	            while (n-- != 0) {
	                if (searchMe.charAt(j++) != substring.charAt(k++)) {
	                    continue test;
	                }
	            }
	            foundIt = true;
	                break test;
	        }
	        System.out.println(foundIt ? "Found it" : "Didn't find it");
	    }
	
	
	/**
	 * break的特殊用法
	 */
	public void breakTest(){
		System.out.println("=========================特殊的break测试=========================");

	      int[][] arrayOfInts = { 
	              { 32, 87, 3, 589 },
	              { 12, 1076, 2000, 8 },
	              { 622, 127, 77, 955 }
	          };
	          int searchfor = 12;

	          int i;
	          int j = 0;
	          boolean foundIt = false;

	      search:
	          for (i = 0; i < arrayOfInts.length; i++) {
	              for (j = 0; j < arrayOfInts[i].length;
	                   j++) {
	                  if (arrayOfInts[i][j] == searchfor) {
	                      foundIt = true;
	                      break search;
	                  }
	              }
	          }

	          if (foundIt) {
	              System.out.println("Found " + searchfor + " at " + i + ", " + j);
	          } else {
	              System.out.println(searchfor + " not in the array");
	          }
	}
	
}


