import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static Palindrome palindrome = new Palindrome();
    @Test

    public void testMyOffByN() {
        OffByN offby5=new OffByN(5);
        assertTrue(offby5.equalChars('a','f'));
        assertTrue(offby5.equalChars('f','a'));
        assertFalse(offby5.equalChars('f','h'));
        assertFalse(offby5.equalChars(' ','h'));

    }

}
