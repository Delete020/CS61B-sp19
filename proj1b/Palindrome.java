public class Palindrome {
    public Deque<Character> wordToDeque(String word) {

        /*
           return a Deque where the characters appear in the same order as in the String.
         */
        Deque<Character> d = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /**
     * return true if the given word is a palindrome, and false otherwise.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> d = this.wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque<Character> d) {
        if (d.size() <= 1) {
            return true;
        }
        if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        return isPalindromeHelper(d);
    }

    /**
     * returns true for characters that are different by exactly one.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = this.wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
