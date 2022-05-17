import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void OffByOneTest() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('z', 'y'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('z', 'z'));
        assertFalse(offByOne.equalChars('z', 'Z'));
        assertFalse(offByOne.equalChars('a', 'c'));
    }
    // Your tests go here.
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/