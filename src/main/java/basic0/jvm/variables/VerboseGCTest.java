package basic0.jvm.variables;

/**
 * 启动jvm时,添加-verbose:gc参数,显示gc信息
 *
 */
@SuppressWarnings("unused")
public class VerboseGCTest {

    public static void main(String[] args) {
//        test0();
//        test1();
    }

    /**
     * 由于局部变量表slot占用的优化,会出现不能及时的将slot中对placeHolder的引用立即释放,导致gc无法回收
     */
    
    private static void test0() {
        byte[] placeHolder = new byte[64 * 1024 * 1024];
        System.gc();
        int i = 100;
    }
    
    /**
     * 手动placeHolder = null,释放引用,可以gc回收
     * <p>但是,不提倡通过手动来释放引用.
     * 因为这只是当前jvm实现不够优秀,java语言规范中的模型是要求自动回收的.
     */
    private static void test1() {
        byte[] placeHolder = new byte[64 * 1024 * 1024];
        placeHolder = null;
        System.gc();
        int i = 100;
    }
}
