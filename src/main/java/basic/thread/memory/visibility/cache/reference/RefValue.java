package basic.thread.memory.visibility.cache.reference;

class RefValue {
    private Object v = null;
    
    public void set(Object newValue) {
        v = newValue;
    }
    public Object get() {
        return v;
    }
}