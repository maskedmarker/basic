package basic.debug.vm;

/**
 * To watch class loading, launch the Java virtual machine with the -verbose flag. You will get a
 * printout such as the following
 *
 */
public class ClassLoadInfoTest {

    /**
     * jvm先打开class的jar文件.然后从Object类开始加载
     * 
     * [Opened C:\Program Files\Java\jdk1.7.0_17\jre\lib\rt.jar]
     * [Loaded java.lang.Object from C:\Program Files\Java\jdk1.7.0_17\jre\lib\rt.jar]
     * [Loaded java.io.Serializable from C:\Program Files\Java\jdk1.7.0_17\jre\lib\rt.jar]
     * [Loaded java.lang.Comparable from C:\Program Files\Java\jdk1.7.0_17\jre\lib\rt.jar]
     * [Loaded java.lang.CharSequence from C:\Program Files\Java\jdk1.7.0_17\jre\lib\rt.jar]
     * [Loaded java.lang.String from C:\Program Files\Java\jdk1.7.0_17\jre\lib\rt.jar]
     * ...
     */
    public static void main(String[] args) {
        String foo = "foo";
        System.out.println(foo);
    }
}
