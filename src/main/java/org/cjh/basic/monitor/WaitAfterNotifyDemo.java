package org.cjh.basic.monitor;

/**
 * notify()仅仅将waiting thread转变为blocked thread,方法本身不会释放锁,但是notify方法需要当前线程在持有锁的情况下使用
 * wait()会释放锁,将当前线程挂起,且当前线程状态变为waiting,被其他线程notify后变为blocked,再之后竞争锁成功才能从wait方法返回;
 */
public class WaitAfterNotifyDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            System.out.println("acquired lock");
            lock.notify();
            System.out.println("after notify thread waiting for condition of lock");
            lock.wait(); //lock number清零,从该方法返回时重新获得锁,此时lock number=1
            System.out.println("after wait");
        }
    }
}
