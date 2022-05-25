package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Color;
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
        List<Card> cards = new ArrayList<>();
        for (String word:words){
            Card card = new Card();
            card.setText(word);
            card.setColor(Color.RED);
            cards.add(card);
        }
        return cards;
    }
}
