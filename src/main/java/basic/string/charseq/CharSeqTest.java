package basic.string.charseq;

import org.junit.Test;

public class CharSeqTest {

    @Test
    public void testBasic() {
        String content = "汉字";
        CharSequence charSeq = content;
        int len = charSeq.length();
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            char c = charSeq.charAt(i);
            System.out.println(c);
            System.out.println((int)c);
        }
    }
    
    /**
     * In the Java SE API documentation, Unicode code point is used for
     * character values in the range between U+0000 and U+10FFFF, and Unicode
     * code unit is used for 16-bit char values that are code units of the
     * UTF-16 encoding. For more information on Unicode terminology, refer to
     * the Unicode Glossary.
     * Java中只能使用U+0000 and U+FFFF之间的字符
     */
    @Test
    public void testChar() {
        int codeUnit = (char)0x2F81A; //截断,取值低4位:F81A
        System.out.println(codeUnit);
        System.out.println(new Character((char)0x2F81A));
        System.out.println(Character.isLetter(0x2F81A));
    }
    
    @Test
    public void testMore() {
        char[] c = Character.toChars(Integer.parseInt("1D306", 16));//1D306是一个辅助平面字符
        for (char i : c) {
            System.out.println(i);
            System.out.println(Integer.toHexString(i));// d834 df06
        }
        System.out.println(new String(c));
        
        System.out.println(Character.codePointAt(c, 0));//输出119558，这个是1D306对应的10进制值
        System.out.println(Character.codePointAt(c, 1));//输出57094，这个是c[1]对应字符的10进制值
        System.out.println(new String(c).codePointAt(0));//输出119558，这个是1D306对应的10进制值
        System.out.println(new String(c).codePointAt(1));//输出57094，这个是c[1]对应字符的10进制值
        String str = "abcdefg" + new String(c);
        System.out.println(str.length());//9
        System.out.println(str.codePointCount(0, str.length()));//8
    }
}
