package basic.thread.interrupt;

import java.util.concurrent.CountDownLatch;



public class WaitTest2 {

	private static Object lock = new Object();
	private static CountDownLatch cdl = new CountDownLatch(1);
	/**
	 * Object.wait()在被调用时,会检查中断状态,如果被标记为true,则清除标记同时抛异常,(查看Object.wait()异常注释)
	 * 在wait过程中被中断,,则清除标记同时抛异常,(查看Object.wait()异常注释)
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Server server = new Server("tom");
		server.start();
		cdl.await();
		
		server.interrupt();
		System.out.println(String.format("%s complete", Thread.currentThread().getName()));
		
	}
	
	private static class Server extends Thread {

		public Server(String name) {
			super(name);
		}
		
		public void run() {
			synchronized (lock) {
				try {
				    cdl.countDown();
					lock.wait();
				} catch (InterruptedException e) {
					System.out.println(String.format("%s has been set interrupted", Thread.currentThread().getName()));
					e.printStackTrace();
				}
			}
		}
	}
}
