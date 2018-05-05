package basic.thread.interrupt;

public class SleepTest {

	/**
	 * Thread.sleep()在被调用时,会检查当前线程(即调用线程)中断状态,如果被标记为true,则清除标记同时抛异常,(查看Thread.sleep()异常注释)
	 */
	public static void main(String[] args) {
		Thread.currentThread().interrupt(); //主动将当前线程的中断位置为true
		System.out.println(String.format("%s 中断标识: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(String.format("%s 中断标识: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
			e.printStackTrace();
		}
	}
}