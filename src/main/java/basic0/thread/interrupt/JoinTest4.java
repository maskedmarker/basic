package basic0.thread.interrupt;

public class JoinTest4 {

	/**
	 * Thread.join()在被调用时,会检查当前线程(即调用线程)中断状态,如果被标记为true,则清除标记同时抛异常,(查看Thread.join()异常注释)
	 */
	public static void main(String[] args) {
		Server server = new Server("tom");
		server.start();
		Interrupter interrupter = new Interrupter("jerry", server, Thread.currentThread());
		interrupter.start();
		
		try {
			server.join(); // 当前线程被interrupt才会抛异常.被join的线程即使interrupted也不会抛异常
		} catch (InterruptedException e) {
		    System.out.println(String.format("%s 从join等待中被中断,恢复active", Thread.currentThread().getName()));
			System.out.println(String.format("%s 中断标识: %s", Thread.currentThread().getName(), Thread.currentThread().isInterrupted()));
			e.printStackTrace();
		}
	}
	
	private static class Server extends Thread {

		public Server(String name) {
			super(name);
		}
		
		public void run() {
			while (true) {
			    if (Thread.interrupted()) {
			        System.out.println(String.format("%s was interrupted", Thread.currentThread().getName()));
			    }
			}
		}
	}
	
	private static class Interrupter extends Thread {
	    private Thread[] targets;
	    public Interrupter(String name, Thread... targets) {
	        super(name);
	        this.targets = targets;
	    }
	    
	    public void run() {
	        for (Thread target : targets) {
	            try {
	                Thread.sleep(5 * 1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            target.interrupt();
	            System.out.println(String.format("%s 中断了 %s", Thread.currentThread().getName(), target.getName()));
	        }
	    }
	}
}
