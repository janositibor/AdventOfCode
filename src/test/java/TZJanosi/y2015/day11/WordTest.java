package TZJanosi.y2015.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    private Word word = new Word();

    @Test
    void increment() {
        word.setWord("cqjxjnds");
        assertEquals("cqjxjnds", word.getWordAsString());
        word.increment();
        assertEquals("cqjxjndt", word.getWordAsString());
        word.increment();
        word.increment();
        word.increment();
        word.increment();
        word.increment();
        word.increment();
        assertEquals("cqjxjndz", word.getWordAsString());
        word.increment();
        assertEquals("cqjxjnea", word.getWordAsString());
    }

    @Test
    void isInc() {
        word.setWord("hijklmmn");
        assertEquals(true, word.isInc());
        word.setWord("abbceffg");
        assertEquals(false, word.isInc());
        word.setWord("abbcegjk");
        assertEquals(false, word.isInc());
        word.setWord("abcdefgh");
        assertEquals(true, word.isInc());
        word.setWord("abcdffaa");
        assertEquals(true, word.isInc());
        word.setWord("ghijklmn");
        assertEquals(true, word.isInc());
        word.setWord("ghjaabcc");
        assertEquals(true, word.isInc());
    }

    @Test
    void containsForbiddenChar() {
        word.setWord("hijklmmn");
        assertEquals(true, word.containsForbiddenChar());
        word.setWord("abbceffg");
        assertEquals(false, word.containsForbiddenChar());
        word.setWord("abbcegjk");
        assertEquals(false, word.containsForbiddenChar());
        word.setWord("abcdefgh");
        assertEquals(false, word.containsForbiddenChar());
        word.setWord("abcdffaa");
        assertEquals(false, word.containsForbiddenChar());
        word.setWord("ghijklmn");
        assertEquals(true, word.containsForbiddenChar());
        word.setWord("ghjaabcc");
        assertEquals(false, word.containsForbiddenChar());
    }

    @Test
    void containsSeparatedPairs() {
        word.setWord("hijklmmn");
        assertEquals(false, word.containsSeparatedPairs());
        word.setWord("abbceffg");
        assertEquals(true, word.containsSeparatedPairs());
        word.setWord("abbcegjk");
        assertEquals(false, word.containsSeparatedPairs());
        word.setWord("abcdefgh");
        assertEquals(false, word.containsSeparatedPairs());
        word.setWord("abcdffaa");
        assertEquals(true, word.containsSeparatedPairs());
        word.setWord("ghijklmn");
        assertEquals(false, word.containsSeparatedPairs());
        word.setWord("ghjaabcc");
        assertEquals(true, word.containsSeparatedPairs());
    }

    @Test
    void isValid() {
        word.setWord("hijklmmn");
        assertEquals(false, word.isValid());
        word.setWord("abbceffg");
        assertEquals(false, word.isValid());
        word.setWord("abbcegjk");
        assertEquals(false, word.isValid());
        word.setWord("abcdefgh");
        assertEquals(false, word.isValid());
        word.setWord("abcdffaa");
        assertEquals(true, word.isValid());
        word.setWord("ghijklmn");
        assertEquals(false, word.isValid());
        word.setWord("ghjaabcc");
        assertEquals(true, word.isValid());
    }

    @Test
    void findNextValidWord() {
        assertEquals("abcdffaa", word.findNextValidWord("abcdefgh"));
        assertEquals("ghjaabcc", word.findNextValidWord("ghijklmn"));
    }

    @Test
    void findNextValidWordForProblemData() {
        assertEquals("cqjxxyzz", word.findNextValidWord("cqjxjnds"));
    }

}