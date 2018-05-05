package org.cjh.basic.thread.memory.visibility.cache.reference2;



class ReadValueFromCache2 implements Runnable {

    private RefValue target;
    
    public ReadValueFromCache2(RefValue target) {
        this.target = target;
    }

    @Override
    public void run() {
        for (int i = 0; i < Long.MAX_VALUE; i++) { //for(;;)在当前compiler下与while(true)等价
            if (target.get() != null) {
                System.out.println("value has been changed: " + target.get());
                break;
            }
        }
    }
}