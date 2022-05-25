package lv.bootcamp.codenames.codenamesgame.model.gameelements;

import java.util.List;

public class GameBoard {
    private List<Card> gameCards;
    private static final int RED_CARD_NUMBER = 9;
    private static final int BLUE_CARD_NUMBER = 8;

    public List<Card> getGameCards() {
        return gameCards;
    }

    public void setGameCards(List<Card> gameCards) {
        this.gameCards = gameCards;
    }
    public boolean checkAllRedCardsRevealed(){
       long redCardsRevealed =  gameCards.stream().filter(Card::isRevealed).filter(a->a.getColor()==Color.RED).count();
       return redCardsRevealed==RED_CARD_NUMBER;
    }
    public boolean checkAllBlueCardsRevealed(){
        long redCardsRevealed =  gameCards.stream().filter(Card::isRevealed).filter(a->a.getColor()==Color.BLUE).count();
        return redCardsRevealed==BLUE_CARD_NUMBER;
    }
    public boolean checkBlackCardRevealed(){
        return gameCards.stream().anyMatch(a->a.isRevealed()&&a.getColor()==Color.BLACK);

    }


}
