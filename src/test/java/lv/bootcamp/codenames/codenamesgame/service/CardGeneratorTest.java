package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.gameelements.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardGeneratorTest {

    @Test
    void generatedListIsNotEmpty() {
        WordGenerator words = new WordGenerator();
        CardGenerator cards = new CardGenerator(words);
        assertFalse(cards.generateCards().isEmpty());
    }

    @Test
    void arrayListConsistOf25Words() {
        WordGenerator words = new WordGenerator();
        CardGenerator cards = new CardGenerator(words);
        assertEquals(25, cards.generateCards().size());
    }

    @Test
    void ListContains9RedCards() {
        WordGenerator words = new WordGenerator();
        CardGenerator cards = new CardGenerator(words);
        int counter = 0;
        for (int i = 0; i < 25; i++)
            if (cards.generateCards().get(i).getColor().equals(Color.RED)) {
                counter++;
            }
        assertEquals(9, counter);
    }

    @Test
    void ListContains8BlueCards() {
        WordGenerator words = new WordGenerator();
        CardGenerator cards = new CardGenerator(words);
        int counter = 0;
        for (int i = 0; i < 25; i++)
            if (cards.generateCards().get(i).getColor().equals(Color.BLUE)) {
                counter++;
            }
        assertEquals(8, counter);
    }

    @Test
    void ListContains7GreenCards() {
        WordGenerator words = new WordGenerator();
        CardGenerator cards = new CardGenerator(words);
        int counter = 0;
        for (int i = 0; i < 25; i++)
            if (cards.generateCards().get(i).getColor().equals(Color.GREEN)) {
                counter++;
            }
        assertEquals(7, counter);
    }

    @Test
    void ListContains1BlackCards() {
        WordGenerator words = new WordGenerator();
        CardGenerator cards = new CardGenerator(words);
        int counter = 0;
        for (int i = 0; i < 25; i++)
            if (cards.generateCards().get(i).getColor().equals(Color.BLACK)) {
                counter++;
            }
        assertEquals(1, counter);
    }
}

