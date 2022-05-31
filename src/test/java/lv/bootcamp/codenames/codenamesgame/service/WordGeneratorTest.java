package lv.bootcamp.codenames.codenamesgame.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordGeneratorTest {

    @Test
    void generatedArrayListIsNotEmpty() {
        WordGenerator words = new WordGenerator();
        assertFalse(words.generateWords().isEmpty());
    }

    @Test
    void arrayListConsistOf25Words() {
        WordGenerator words = new WordGenerator();
        assertEquals(25, words.generateWords().size());
    }

    @Test
    void twoArrayListsAreNotSimilar() {
        WordGenerator words = new WordGenerator();
        WordGenerator words1 = new WordGenerator();
        assertFalse(words.generateWords().equals(words1.generateWords()));
    }

    @Test
    void noTheSameWordsInArrayList() {
        WordGenerator words = new WordGenerator();
        for (int i = 1; i < 25; i++) {
          assertFalse(words.generateWords().get(i).equals(words.generateWords().get(i-1)));
        }
    }
}