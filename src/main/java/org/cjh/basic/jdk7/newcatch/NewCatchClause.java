package org.cjh.basic.jdk7.newcatch;

public class NewCatchClause {

    public static void main(String[] args) {
        boolean illegal = false;
        boolean nullPointer = true;
        try {
            if (illegal) {
                throw new IllegalArgumentException("bad arg");
            }
            if (nullPointer) {
                throw new NullPointerException("bad arg");
            }
        } catch (IllegalArgumentException | NullPointerException e) { // JDK7新特性
            System.out.println(e.getClass().getName());
        }
        
    }
}
