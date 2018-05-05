package org.cjh.basic.thread.memory.visibility.cache.primitive;


class ChangeValueTask implements Runnable {
    
    private BoolValue target;
    
    public ChangeValueTask(BoolValue target) {
        this.target = target;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(5 * 1000);
            System.out.println(Thread.currentThread().getName() + "waked up");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        target.set(true);
        System.out.println("value has been changed to: " + target.get());
    }
}