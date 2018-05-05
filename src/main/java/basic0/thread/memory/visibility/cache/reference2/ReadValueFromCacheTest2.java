package basic0.thread.memory.visibility.cache.reference2;




/**
 * 没有加volatile的属性,工作内存中的新值返写入主内存的[时间不定].这就是导致内存可见性的原因.
 * 用while循环是比较典型的例子特别容易触发,用for不容易触发,多试几次才会出现.
 */
public class ReadValueFromCacheTest2 {

    public static void main(String[] args) {
        RefValue val = new RefValue();
        Thread cv = new Thread(new ChangeValueTask2(val));
        Thread rv = new Thread(new ReadValueFromCache2(val));
        rv.start();
        cv.start();
    }
}
