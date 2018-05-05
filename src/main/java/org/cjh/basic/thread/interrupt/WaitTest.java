package org.cjh.basic.thread.interrupt;



public class WaitTest {

	private static Object lock = new Object();
	/**
	 * Object.wait()在被调用时,会检查中断状态,如果被标记为true,则清除标记同时抛异常,(查看Object.wait()异常注释)
	 * 在wait过程中被中断,,则清除标记同时抛异常,(查看Object.wait()异常注释)
	 */
	public static void main(String[] args) {
		
		Server server = new Server("tom");
		server.start();
		
	}
	
	private static class Server extends Thread {

		public Server(String name) {
			super(name);
		}
		
		public void run() {
			Thread.currentThread().interrupt(); //主动将当前线程的中断位置为true
			System.out.println(String.format("%s has been set interrupted", Thread.currentThread().getName()));
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
