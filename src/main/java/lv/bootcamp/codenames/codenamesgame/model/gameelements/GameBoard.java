package lv.bootcamp.codenames.codenamesgame.model.gameelements;

import java.util.List;

public class GameBoard {
    private List<Card> gameCards;

    public List<Card> getGameCards() {
        return gameCards;
    }

    public void setGameCards(List<Card> gameCards) {
        this.gameCards = gameCards;
    }
}
