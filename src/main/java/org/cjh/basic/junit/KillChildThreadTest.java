package org.cjh.basic.junit;

import org.junit.Test;

/**
 * Junit不支持多线程,当主线程结束时,调用System.exit(0),jvm实例退出,子线也就over了;
 * 所以,可以采用Thread.join()来等待子线程结束.
 * 通过main方法启动jvm实例做单元测试,则等待子线程结束后,jvm实例才退出.
 */
public class KillChildThreadTest {

    @Test
    public void killChildThread() {
        new Thread(new InfinitLoop()).start();
        // main线程wake up 之后,main线程结束,jvm实例退出
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread exit");
    }
    
    @Test
    public void waitChildThread() {
        Thread subThread = new Thread(new InfinitLoop());
        subThread.start();
        // main线程wake up 之后,main线程结束,jvm实例退出
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 等待子线程结束,main线程再结束
        try {
            subThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread exit");
    }
    
    
    
    private static class InfinitLoop implements Runnable {

        @Override
        public void run() {
            int counter = 0;
            char[] cs = {'/', '\\'};
            while (true) {
                System.out.println(cs[counter++]);
                counter = counter % 2;
            }
        }
        
    }
}
