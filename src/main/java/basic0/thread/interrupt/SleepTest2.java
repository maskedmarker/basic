package basic0.thread.interrupt;


public class SleepTest2 {

	/**
	 * Thread.sleep()的过程中,如果被其他线程interrupt(),则前者会从sleep中重新变得active,并清除标记同时抛异常,(查看Thread.sleep()异常注释)
	 */
	public static void main(String[] args) {
		
		Server server = new Server("tom");
		server.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		server.interrupt();
		System.out.println(String.format("%s interrupted %s", Thread.currentThread().getName(), server.getName()));
		
	}
	
	private static class Server extends Thread {

		public Server(String name) {
			super(name);
		}
		
		public void run() {
			try {
				Thread.sleep(1000 * 1000);
			} catch (InterruptedException e) {
				System.out.println(String.format("%s has been set interrupted", Thread.currentThread().getName()));
				e.printStackTrace();
			}
			System.out.println(String.format("%s end", Thread.currentThread().getName()));
		}
	}
}