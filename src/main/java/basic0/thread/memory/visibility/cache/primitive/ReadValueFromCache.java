package basic0.thread.memory.visibility.cache.primitive;


class ReadValueFromCache implements Runnable {

    private BoolValue target;
    
    public ReadValueFromCache(BoolValue target) {
        this.target = target;
    }

    @Override
    public void run() {
        while (true) {
            if (target.get()) {
                System.out.println("value has been changed: " + target.get());
                break;
            }
        }
    }
}