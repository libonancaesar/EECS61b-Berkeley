import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean D1 = palindrome.isPalindrome("SbS");
        boolean D2 = palindrome.isPalindrome("a");
        boolean D3 = palindrome.isPalindrome("panama");
        boolean D4 = palindrome.isPalindrome("Bonanli");
        assertTrue( D1 == true);
        assertTrue(D2 == true);
        assertTrue(D3 == false);
        assertFalse(D4 == true);
    }
    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("ababssba", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("f", offByOne));
        assertFalse(palindrome.isPalindrome("aks", offByOne));


    }

}     /*Uncomment this class once you've created your Palindrome class. */