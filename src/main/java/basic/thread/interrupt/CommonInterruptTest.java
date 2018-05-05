package basic.thread.interrupt;

/**
 * 非阻塞线程，设置中断标识位
 *
 */
public class CommonInterruptTest {

	public static void main(String[] args) {
		InterruptedThread itt = new InterruptedThread("InterruptedThread");
		itt.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 设置线程的中断标志位
		itt.interrupt();
	}

	
	private static class InterruptedThread extends Thread {
		
		public InterruptedThread(String name) {
			super(name);
		}

		public void run() {
			// 这里调用的是非清除中断标志位的isInterrupted方法
			while (!Thread.currentThread().isInterrupted()) {
				long beginTime = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName() + "is running");
				// 当前线程每隔一秒钟检测线程中断标志位是否被设置(不使用锁/Thread.sleep(),防止抛异常)
				while (System.currentTimeMillis() - beginTime < 1000) {
				}
			}
			if (Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread().getName() + " is interrupted");
			}
		}
	}
}