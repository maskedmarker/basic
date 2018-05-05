package org.cjh.basic.thread.interrupt;

import java.util.concurrent.CountDownLatch;

public class JoinTest2 {

	private static final CountDownLatch counter = new CountDownLatch(1);
	/**
	 * Thread.join()在被调用时,会检查当前线程(即调用线程)中断状态,如果被标记为true,则清除标记同时抛异常,(查看Thread.join()异常注释)
	 * 但是不会检查被调用线程的中断状态
	 */
	public static void main(String[] args) {
		
		Server server = new Server("tom");
		server.start();
		try {
			counter.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Thread.currentThread().interrupt(); //主动将当前线程的中断位置为true
		System.out.println(String.format("%s 中断标识: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
		try {
			server.join(10 * 1000);//当前线程被interrupt才会抛异常.被join的线程即使interrupted也不会抛异常
		} catch (InterruptedException e) {
		    System.out.println("Thread.join()调用时,会检查调用线程的interrupted status, 如果调用线程被其他线程interrupt过,则调用join()时抛异常");
		    System.out.println(String.format("%s 中断标识: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
		    if (!Thread.currentThread().isInterrupted()) {
			    System.out.println("The interrupted status of the current thread is cleared when this exception is thrown.");
			}
			e.printStackTrace();
		}
		System.out.println("main thread complete");
	}
	
	private static class Server extends Thread {

		public Server(String name) {
			super(name);
		}
		
		public void run() {
			counter.countDown();
			while (true) {
				//do nothing
			}
		}
	}
}
