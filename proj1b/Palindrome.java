import java.util.Objects;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> LLD = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i+=1) {
            LLD.addLast(word.charAt(i));
        }
        return LLD;
    }

    /* identify if the word is Palindrome using recursion */
    public boolean isPalindrome(String word) {
        Deque<Character> LLD = wordToDeque(word);
        return isPalindromeHelper(LLD);
    }

    private boolean isPalindromeHelper(Deque d) {
        if (d.size() < 2) {
            return true;
        }
        if (d.getFirst() != d.getLast()) {
            return false;
        }
        d.removeFirst();
        d.removeLast();
        return isPalindromeHelper(d);
    }

    /* A second method for the comparison of two strings using str class */
    public boolean isPalindrome (String word, CharacterComparator cc) {
        Deque<Character> LLD = wordToDeque(word);
        while (LLD.size() > 1){
            if (!cc.equalChars(LLD.getFirst(),LLD.getLast()))
                return false;
            LLD.removeFirst();
            LLD.removeLast();
        }
        return true;

    }

}
