package basic0.thread.memory.visibility.cache.primitive;

import org.junit.Test;


public class ReadValueFromCacheTest {

    @Test
    public void readValueFromCache() {
        System.out.println(Thread.currentThread().getName() + " moved to main method");
        BoolValue val = new BoolValue();
        Thread cv = new Thread(new ChangeValueTask(val));
        Thread rv = new Thread(new ReadValueFromCache(val));
        rv.setDaemon(true);
        cv.start();
        rv.start();
        
        try {
            cv.join();
            System.out.println("ChangeValueTask complete");
            rv.join(); // 阻塞主线程直到rv结束,不然Junit不杀掉子线程.
            System.out.println("ReadValueFromCache complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
