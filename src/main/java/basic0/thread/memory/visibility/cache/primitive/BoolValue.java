package basic0.thread.memory.visibility.cache.primitive;

class BoolValue {
    private boolean v = false;
    
    public void set(boolean newValue) {
        v = newValue;
    }
    public boolean get() {
        return v;
    }
}