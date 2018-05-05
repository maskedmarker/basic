package basic.thread.sync;

/**
 * wait(timeout)超时后,等待其他线程释放monitor,然后去争抢monitor.
 *
 */
public class WaitTimeoutTest {

    private static Object lock = new Object();
    
    private static class Peer extends Thread{
        private String name;
        private long timeout;

        public Peer(String name, long timeout) {
            super();
            this.name = name;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(name + "before");
                try {
                    lock.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(name + "interrupted");
                }
                System.out.println(name + "after");
            }
        }
    }
    
    private static class Machine extends Thread{
        private String name;
        private boolean forever;
        public Machine(String name, boolean forever) {
            super();
            this.name = name;
            this.forever = forever;
        }
        
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(name + "before");
                while(forever) {
                    //
                }
                System.out.println(name + "after");
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        Peer p1 = new Peer("p1", 1000);
//        Peer p2 = new Peer("p2", 10000);
        Machine m2 = new Machine("m2", true);
        
        p1.start();
//        p2.start();
        m2.start();
        
        p1.join();
//        p2.join();
        m2.join();
        
        System.out.println("main exit");
    }
}
