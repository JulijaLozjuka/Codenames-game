package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardGenerator {
    private final WordGenerator wordGenerator;

    public CardGenerator(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }
    public List<Card> generateCards(){
        List<String> words = wordGenerator.generateWords();

        return new ArrayList<Card>();
    }
}
