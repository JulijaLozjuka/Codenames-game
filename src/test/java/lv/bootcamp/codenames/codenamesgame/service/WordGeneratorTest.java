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
    void noTheSameWordsInArrayList() {
        WordGenerator words = new WordGenerator();
        for (int i = 0; i < words.generateWords().size(); i++) {
            String testString = words.generateWords().get(i);
            for (int j = 0; i < words.generateWords().size(); i++) {
                if (i == j)
                    break;
                else {
                    assertFalse(words.generateWords().get(j).equals(testString));
                }
            }
        }
    }
}