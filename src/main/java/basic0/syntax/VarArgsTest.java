package basic0.syntax;

/**
 * 变长参数与相应的数组参数是完全等价的,仅仅是个语法糖
 * 不同之处仅仅是在开发工具中编译器的提示.
 *
 */
public class VarArgsTest {

    public static void main(String[] args) {
        String[] msgs = {"hello", "new", "world"};
        foo(msgs);
        foo((String[])null);
        //The argument of type null should explicitly be cast to String[] 
        //for the invocation of the varargs method foo(String...) from type VarArrayTest. 
        //It could alternatively be cast to String for a varargs invocation
        foo((String[])null);
        //这里编译器不会提示类型转换
        goo(null);
        foo();
    }
    
    private static void foo(String... msgs) {
        System.out.println(msgs.length);
    }
    
    private static void goo(String[] msgs) {
        System.out.println(msgs.length);
    }
}
