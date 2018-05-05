package org.cjh.basic.thread.memory.visibility.cache.reference;



public class ReadValueFromCacheTest {

    public static void main(String[] args) {
        RefValue val = new RefValue();
        Thread cv = new Thread(new ChangeValueTask(val));
        Thread rv = new Thread(new ReadValueFromCache(val));
        cv.start();
        rv.start();
    }
}
