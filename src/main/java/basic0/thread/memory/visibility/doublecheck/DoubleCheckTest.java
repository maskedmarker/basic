package basic0.thread.memory.visibility.doublecheck;

/**
 * http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
 * 
 * Paul Jakubik found an example of a use of double-checked locking that did not work correctly. 
 * A slightly cleaned up version of that code is available here.
 * http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckTest.java
 * When run on a system using the Symantec JIT, it doesn't work. 
 * In particular, the Symantec JIT compiles
 * singletons[i].reference = new Singleton();
 * 
 * 
 * to the following (note that the Symantec JIT using a handle-based object allocation system).

 * 0206106A   mov         eax,0F97E78h
 * 0206106F   call        01F6B210                  ; allocate space for
 *                                                  ; Singleton, return result in eax
 * 02061074   mov         dword ptr [ebp],eax       ; EBP is &singletons[i].reference 
 *                                                 ; store the unconstructed object here.
 * 02061077   mov         ecx,dword ptr [eax]       ; dereference the handle to
 *                                                  ; get the raw pointer
 * 02061079   mov         dword ptr [ecx],100h      ; Next 4 lines are
 * 0206107F   mov         dword ptr [ecx+4],200h    ; Singleton's inlined constructor
 * 02061086   mov         dword ptr [ecx+8],400h
 * 0206108D   mov         dword ptr [ecx+0Ch],0F84030h
 * 
 * As you can see, 
 * the assignment to singletons[i].reference is performed before the constructor for Singleton is called. 
 * This is completely legal under the existing Java memory model, 
 * and also legal in C and C++ (since neither of them have a memory model).
 * 
 * 
 * 
 * Java虚拟机有3种执行方式，分别是解释执行、混合模式和编译执行，默认情况下处于混合模式中。使用命令行java –version可以查看虚拟机的执行模式：
 */
public class DoubleCheckTest {

    // static data to aid in creating N singletons
    static final Object dummyObject = new Object(); // for reference init
    static final int A_VALUE = 256; // value to initialize 'a' to
    static final int B_VALUE = 512; // value to initialize 'b' to
    static final int C_VALUE = 1024;
    static ObjectHolder[] singletons; // array of static references
    static Thread[] threads; // array of racing threads
    static int threadCount; // number of threads to create
    static int singletonCount; // number of singletons to create

    static volatile int recentSingleton;

    // I am going to set a couple of threads racing,
    // trying to create N singletons. Basically the
    // race is to initialize a single array of
    // singleton references. The threads will use
    // double checked locking to control who
    // initializes what. Any thread that does not
    // initialize a particular singleton will check
    // to see if it sees a partially initialized view.
    // To keep from getting accidental synchronization,
    // each singleton is stored in an ObjectHolder
    // and the ObjectHolder is used for
    // synchronization. In the end the structure
    // is not exactly a singleton, but should be a
    // close enough approximation.
    //

    // This class contains data and simulates a
    // singleton. The static reference is stored in
    // a static array in DoubleCheckFail.
    static class Singleton {
        public int a;
        public int b;
        public int c;
        public Object dummy;

        public Singleton() {
            a = A_VALUE;
            b = B_VALUE;
            c = C_VALUE;
            dummy = dummyObject;
        }
    }

    static void checkSingleton(Singleton s, int index) {
        int s_a = s.a;
        int s_b = s.b;
        int s_c = s.c;
        Object s_d = s.dummy;
        if (s_a != A_VALUE)
            System.out.println("[" + index + "] Singleton.a not initialized " + s_a);
        if (s_b != B_VALUE)
            System.out.println("[" + index + "] Singleton.b not intialized " + s_b);

        if (s_c != C_VALUE)
            System.out.println("[" + index + "] Singleton.c not intialized " + s_c);

        if (s_d != dummyObject)
            if (s_d == null)
                System.out.println("[" + index + "] Singleton.dummy not initialized," + " value is null");
            else
                System.out.println("[" + index + "] Singleton.dummy not initialized," + " value is garbage");
    }

    // Holder used for synchronization of
    // singleton initialization.
    static class ObjectHolder {
        public Singleton reference;
    }

    static class TestThread implements Runnable {
        public void run() {
            for (int i = 0; i < singletonCount; ++i) {
                ObjectHolder o = singletons[i];
                if (o.reference == null) {
                    synchronized (o) {
                        if (o.reference == null) {
                            o.reference = new Singleton();
                            recentSingleton = i;
                        }
                        // shouldn't have to check singelton here
                        // mutex should provide consistent view
                    }
                } else {
                    checkSingleton(o.reference, i);
                    int j = recentSingleton - 1;
                    if (j > i)
                        i = j;
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java DoubleCheckFail" + " <numThreads> <numSingletons>");
        }
        // read values from args
        threadCount = Integer.parseInt(args[0]);
        singletonCount = Integer.parseInt(args[1]);

        // create arrays
        threads = new Thread[threadCount];
        singletons = new ObjectHolder[singletonCount];

        // fill singleton array
        for (int i = 0; i < singletonCount; ++i)
            singletons[i] = new ObjectHolder();

        // fill thread array
        for (int i = 0; i < threadCount; ++i)
            threads[i] = new Thread(new TestThread());

        // start threads
        for (int i = 0; i < threadCount; ++i)
            threads[i].start();

        // wait for threads to finish
        for (int i = 0; i < threadCount; ++i) {
            try {
//                System.out.println("waiting to join " + i);
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println("interrupted");
            }
        }
        System.out.println("done");
    }
}