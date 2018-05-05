package org.cjh.basic.thread.memory.visibility.cache.reference2;



class ChangeValueTask2 implements Runnable {
    
    private RefValue target;
    
    public ChangeValueTask2(RefValue target) {
        this.target = target;
    }
    
    @Override
    public void run() {
        target.set(new Object());
        System.out.println("value has been changed by ChangeValueTask2 to: " + target.get());
    }
}