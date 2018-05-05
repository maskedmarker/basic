package basic.thread.memory.visibility.cache.reference;


class ChangeValueTask implements Runnable {
    
    private RefValue target;
    
    public ChangeValueTask(RefValue target) {
        this.target = target;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(10);
            System.out.println(Thread.currentThread().getName() + "waked up");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        target.set(new Object());
        System.out.println("value has been changed to: " + target.get());
    }
}