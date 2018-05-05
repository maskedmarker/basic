package basic0.thread.memory.visibility.cache.reference;


class ReadValueFromCache implements Runnable {

    private RefValue target;
    
    public ReadValueFromCache(RefValue target) {
        this.target = target;
    }

    @Override
    public void run() {
        while (true) {
            if (target.get() != null) {
                System.out.println("value has been changed: " + target.get());
                break;
            }
        }
    }
}