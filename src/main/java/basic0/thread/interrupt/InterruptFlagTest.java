package basic0.thread.interrupt;

public class InterruptFlagTest {

    /**
     * Thread.interrupt()对于处于非阻塞状态的线程，跟普通设置boolean属性一样; 
     * 对于处于阻塞状态的线程,则会有异常抛出
     */
    public static void main(String[] args) {
        System.out.println("before interruption: the current thread is interrupted? " + Thread.currentThread().isInterrupted());
        // 主动将当前线程的中断位置为true
        Thread.currentThread().interrupt();
        System.out.println("after interruption: the current thread is interrupted? " + Thread.currentThread().isInterrupted());
    }
}
