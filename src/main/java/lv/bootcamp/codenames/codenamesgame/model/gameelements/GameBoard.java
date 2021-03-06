package lv.bootcamp.codenames.codenamesgame.model.gameelements;

import java.util.List;

public class GameBoard {
    private List<Card> gameCards;
    private Hint hint;
    public static final int RED_CARD_NUMBER = 9;
    public static final int BLUE_CARD_NUMBER = 8;

    public GameBoard() {
        hint = new Hint("No clue yet");
    }

    public List<Card> getGameCards() {
        return gameCards;
    }

    public void setGameCards(List<Card> gameCards) {
        this.gameCards = gameCards;
    }

    public boolean checkAllRedCardsRevealed() {
        long redCardsRevealed = gameCards.stream().filter(Card::isRevealed).filter(a -> a.getColor() == Color.RED).count();
        return redCardsRevealed == RED_CARD_NUMBER;
    }

    public boolean checkAllBlueCardsRevealed() {
        long redCardsRevealed = gameCards.stream().filter(Card::isRevealed).filter(a -> a.getColor() == Color.BLUE).count();
        return redCardsRevealed == BLUE_CARD_NUMBER;
    }

    public boolean checkBlackCardRevealed() {
        return gameCards.stream().anyMatch(a -> a.isRevealed() && a.getColor() == Color.BLACK);

    }

    public Hint getHint() {
        return hint;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }
}
