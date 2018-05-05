package org.cjh.basic.enumeration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * 通过javap查看字节码，对枚举类的特性了解的特别清晰
 *
 */
public class EnumTest {

    static final int SYNTHETIC = 0x00001000;
    
    /**
     * An enum declaration is implicitly final unless it contains at least one enum constant that has a class body
     */
    @Test
    public void testClassModifier() {
        int classModifiers = OrderSide.class.getModifiers();
        System.out.println(Modifier.isFinal(classModifiers));
    }
    
    /**
     * 
     */
    @Test
    public void testEnumConstantModifier() {
        Field[] fields = OrderSide.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldModifiers = field.getModifiers();
            boolean synthetic = (SYNTHETIC & fieldModifiers) != 0;
            System.out.println(fieldName + "|" + Modifier.isStatic(fieldModifiers) + "|" + synthetic);
        }
    }
    
    /**
     * synthesized by the compiler
     */
    @Test
    public void testSyntheticField() {
        Field[] fields = OrderSide.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldModifiers = field.getModifiers();
            boolean synthetic = (SYNTHETIC & fieldModifiers) != 0;
            System.out.println(fieldName + "|" + synthetic);
        }
    }
    
    @Test
    public void testSyntheticMethod() {
        Method[] methods = OrderSide.class.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            int methodModifiers = method.getModifiers();
            boolean synthetic = (SYNTHETIC & methodModifiers) != 0;
            System.out.println(methodName + "|" + synthetic);
        }
    }
    
    /**
     * An enum type has no instances other than those defined by its enum constants.
     * 普通类的默认构造函数为public，而枚举类默认构造函数为private，这样可以防止枚举类在外部被实例化，
     * 枚举类常量，也即枚举类的实例，是在枚举类被加载初始化时，在static代码块中被实例化。
     * static块中调用private构造器，符合普通类的一般规范。
     * 
     * 枚举类与普通类的不同之处是，反射调用private构造器构造实例会报错，反序列化构造实例也会报错，通过clone构造实例也会报错。
     */
    @Test
    public void testConstructorModifier() {
        Constructor<?>[] constructors = OrderSide.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            String constructorName = constructor.getName();
            int constructorModifiers = constructor.getModifiers();
            System.out.println(constructorName + "|" + Modifier.isPrivate(constructorModifiers));
        }
    }
    
    @Test
    public void testSyntheticConstructor() {
        Constructor<?>[] constructors = OrderSide.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            String constructorName = constructor.getName();
            int constructorModifiers = constructor.getModifiers();
            boolean synthetic = (SYNTHETIC & constructorModifiers) != 0;
            System.out.println(constructorName + "|"  + synthetic);
        }
    }
}
