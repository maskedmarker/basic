package org.cjh.basic.thread.memory.visibility.cache.primitive;

class BoolValue {
    private boolean v = false;
    
    public void set(boolean newValue) {
        v = newValue;
    }
    public boolean get() {
        return v;
    }
}