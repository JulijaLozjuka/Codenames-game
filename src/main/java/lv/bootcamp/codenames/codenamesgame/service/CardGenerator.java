package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CardGenerator {
    private final WordGenerator wordGenerator;

    public CardGenerator(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }
    public List<Card> generateCards(){
        List<String> words = wordGenerator.generateWords();

        ArrayList<Card> gameCards = new ArrayList<>();

        for (String element : words) {
            Card card = new Card();
            card.setText(element);
            card.setRevealed(false);
            gameCards.add(card);
        }

        for (int i = 0; i < 9; i++) {
            gameCards.get(i).setColor(Color.RED);
        }
        for (int i = 9; i < 17; i++) {
            gameCards.get(i).setColor(Color.BLUE);
        }
        for (int i = 17; i < 24; i++) {
            gameCards.get(i).setColor(Color.GREEN);
        }
        gameCards.get(gameCards.size()-1).setColor(Color.BLACK);
        Collections.shuffle(gameCards);

        return gameCards;
    }
}