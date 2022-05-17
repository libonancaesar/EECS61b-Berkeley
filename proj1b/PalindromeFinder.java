/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        CharacterComparator offByN = new OffByN(2);
        int minLength = 4;
        In in = new In("C:\\Users\\bonanl\\Desktop\\Learning materials\\EECS61b-Berkeley-master\\library-sp19\\data\\words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
        if (word.length() >= minLength && palindrome.isPalindrome(word, offByN)) {
                System.out.println(word);
            }
        }
    }
} //Uncomment this class once you've written isPalindrome. */