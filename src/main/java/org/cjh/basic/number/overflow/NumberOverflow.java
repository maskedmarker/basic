package org.cjh.basic.number.overflow;

import org.junit.Test;

/**
 * java中基本数值型变量也存在溢出的状况，而且jvm并不报错
 *
 */
public class NumberOverflow {

    @Test
    public void integerOverflow() {
        int i = 1;
        while (i > 0) {
            i++;
        }
        System.out.println("integer overflow");
        System.out.println("current value is " + i);
        if (Integer.MIN_VALUE == i) {
            System.out.println("integer类型数据溢出后变为最小值,即出现取模式的循环.这样的设计是因为特定类型的数据在内存中占的bit长度是固定的");
        }
    }
    
    @Test
    public void shortOverflow() {
        short i = 1;
        while (i > 0) {
            i++;
        }
        System.out.println("integer overflow");
        System.out.println("current value is " + i);
        if (Short.MIN_VALUE == i) {
            System.out.println("short类型数据溢出后变为最小值,即出现取模式的循环.这样的设计是因为特定类型的数据在内存中占的bit长度是固定的");
        }
    }
    
    @Test
    public void longOverflow() {
        long i = 1L;
        while (i > 0L) {
            i += 100000000000000L;
            System.out.println(i);
        }
        System.out.println("integer overflow");
        System.out.println("current value is " + i);
        if (Long.MIN_VALUE == i) {
            System.out.println("long类型数据溢出后变为最小值,即出现取模式的循环.这样的设计是因为特定类型的数据在内存中占的bit长度是固定的");
        }
    }
    
    /**
     * <b>注意:</b> float类型比较特殊,会出现Infinity
     */
    @Test
    public void floatOverflow() {
        float i = 1.0F;
        while (i > 0) {
            i += 1000000000000000000000000000000000F;
            System.out.println(i);
        }
        System.out.println("integer overflow");
        System.out.println("current value is " + i);
        if (Short.MIN_VALUE == i) {
            System.out.println("float类型数据溢出后变为最小值,即出现取模式的循环.这样的设计是因为特定类型的数据在内存中占的bit长度是固定的");
        }
    }
    
    /**
     * <b>注意:</b> double类型比较特殊,会出现Infinity
     */
    @Test
    public void doubleOverflow() {
        double i = 1.0F;
        while (i > 0) {
            i += 1e302D;
            System.out.println(i);
        }
        System.out.println("integer overflow");
        System.out.println("current value is " + i);
        if (Short.MIN_VALUE == i) {
            System.out.println("double类型数据溢出后变为最小值,即出现取模式的循环.这样的设计是因为特定类型的数据在内存中占的bit长度是固定的");
        }
    }
}
