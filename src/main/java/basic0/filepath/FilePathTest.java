package basic0.filepath;

import java.io.File;

import org.junit.Test;

public class FilePathTest {

    /**
     * java中的绝对地址是
     * windows:所在卷的根目录
     * linux:系统根目录
     */
    @Test
    public void absPath() {
        String path = "/workspace";
        File f = new File(path);
        System.out.println(f.exists());
    }
    
    /**
     * java中的相对地址是相对于user.dir(即工程的目录)
     */
    @Test
    public void relativePath() {
        String path = ".project";
        File f = new File(path);
        System.out.println(f.exists());
    }
    
    @Test
    public void userDir() {
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }
    
    
}
