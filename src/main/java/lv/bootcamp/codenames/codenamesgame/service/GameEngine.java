package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import lv.bootcamp.codenames.codenamesgame.model.PlayerTurnStatus;
import lv.bootcamp.codenames.codenamesgame.model.Team;
import lv.bootcamp.codenames.codenamesgame.model.TurnData;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Color;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.GameBoard;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Hint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static lv.bootcamp.codenames.codenamesgame.model.gameelements.GameBoard.BLUE_CARD_NUMBER;
import static lv.bootcamp.codenames.codenamesgame.model.gameelements.GameBoard.RED_CARD_NUMBER;

@Service
public class GameEngine {

    private final Logger logger = LoggerFactory.getLogger(GameEngine.class);

    private final static int FULL_TEAMS = 4;

    private Player activePlayer;

    private String winner;

    private Team redTeam;
    private Team blueTeam;
    private GameBoard gameBoard;
    private final CardGenerator cardGenerator;


    public GameEngine(CardGenerator cardGenerator) {
        this.redTeam = new Team();
        this.blueTeam = new Team();
        this.cardGenerator = cardGenerator;

        // for testing must delete this

        redTeam.setSpymaster(new Player("Julia"));
        redTeam.setOperative(new Player("Evans"));
        blueTeam.setSpymaster(new Player("Alla"));
        blueTeam.setOperative(new Player("Andris"));

        startGame();

        //activePlayer = redTeam.getOperative();
    }

    public void addPlayer(Player player) {
        logger.info("Adding player " + player.getName());
        if (redTeam.isTeamFull() && blueTeam.isTeamFull()) {
            return;
        }
        if (!redTeam.isTeamFull()) {
            redTeam.addPlayer(player);
        } else {
            blueTeam.addPlayer(player);
        }
        if (allPlayersReady()) {
            startGame();
        }
    }

    public void startGame() {
        logger.info("Starting game.");
        gameBoard = new GameBoard();
        List<Card> gameCards = cardGenerator.generateCards();
        gameBoard.setGameCards(gameCards);
        activePlayer = redTeam.getSpymaster();
        logger.info("Game started");
        logger.info("New Active-player: " + activePlayer.getName());
    }

    public void passTheTurnToTheNextPlayer() {
        if (activePlayer.getName().equals(redTeam.getSpymaster().getName())) {
            activePlayer = redTeam.getOperative();
        } else if (activePlayer.getName().equals(redTeam.getOperative().getName())) {
            activePlayer = blueTeam.getSpymaster();
        } else if (activePlayer.getName().equals(blueTeam.getSpymaster().getName())) {
            activePlayer = blueTeam.getOperative();
        } else if (activePlayer.getName().equals(blueTeam.getOperative().getName())) {
            activePlayer = redTeam.getSpymaster();
        }

        logger.info("New Active-player: " + activePlayer.getName());
    }

    public PlayerTurnStatus checkPlayer(String playerName) {
        PlayerTurnStatus result = new PlayerTurnStatus();

        if (activePlayer.getName().equals(playerName)) {
            result.setPlayersTurn(true);
        }

        if (redTeam.getSpymaster().getName().equals(playerName)
                || blueTeam.getSpymaster().getName().equals(playerName)) {
            result.setSpymaster(true);
        }

        return result;

    }

    public int getPlayerCount() {
        return redTeam.getPlayerCount() + blueTeam.getPlayerCount();
    }

    public List<Player> getPlayerList() {
        return Stream.of(redTeam.getOperative(), redTeam.getSpymaster(), blueTeam.getOperative(), blueTeam.getSpymaster())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public boolean allPlayersReady() {
        return getPlayerCount() == FULL_TEAMS;
    }


    public void operativeMove(String playerName, String word) {
        logger.info("Operative move");
        List<Card> cardList = gameBoard.getGameCards();

        Card openedCard = null;
        for (Card card : cardList) {
            if (word.equals(card.getText())) {
                card.setRevealed(true);
                openedCard = card;
                logger.info("Card revealed: " + card);
                break;
            }
        }

        if (allRedOpened() || allBlueOpened()) {
            endGameAllCardsOpened(playerName);
        } else if (Color.BLACK.equals(openedCard.getColor())) {
            endGameBlackCard(playerName);
        }

        if (!openedCard.getColor().equals(getPLayerColor(playerName))) {
            passTheTurnToTheNextPlayer();
        }
    }

    private void endGameBlackCard(String playerName) {
        logger.info("GAME END BLACK CARD opened");
        winner = Color.RED.equals(getPLayerColor(playerName))
                ? blueTeam.getSpymaster().getName()
                : redTeam.getSpymaster().getName();
    }

    private void endGameAllCardsOpened(String playerName) {
        logger.info("GAME END ALL card opened");
        winner = playerName;
    }

    private boolean allBlueOpened() {
        return gameBoard.getGameCards().stream()
                .filter(Card::isRevealed)
                .filter(card -> card.getColor().equals(Color.BLUE))
                .count() == BLUE_CARD_NUMBER;
    }

    private boolean allRedOpened() {
        return gameBoard.getGameCards().stream()
                .filter(Card::isRevealed)
                .filter(card -> card.getColor().equals(Color.RED))
                .count() == RED_CARD_NUMBER;
    }

    public Color getPLayerColor(String playerName) {
        return redTeam.getSpymaster().getName().equals(playerName) || redTeam.getOperative().getName().equals(playerName)
                ? Color.RED
                : Color.BLUE;
    }

    public void spymasterMove(Hint hint) {
        logger.info("Spymaster move");
        setHint(hint);
        passTheTurnToTheNextPlayer();
    }

    public void playerMove(String playerName, TurnData turnData) {
        String cardText = turnData.getSelectedCard().getText();
        if (StringUtils.hasText(cardText)) {
            operativeMove(playerName, cardText);
        } else {
            spymasterMove(turnData.getHint());
        }
    }

    public List<Card> getGameBoard() {
        return gameBoard.getGameCards();
    }

    public void setHint(Hint hint) {
        logger.info("Setting hint: " + hint.getText());
        gameBoard.setHint(hint);
    }

    public Boolean isGameEnd() {
        return !Objects.isNull(winner);
    }

    public String getWinningTeam() {
        return getPLayerColor(winner).toString();
    }

    private void resetGame() {
        logger.info("Resetting game");
        winner = null;
        startGame();
    }

    public void restart() {
        resetGame();
    }
    public Hint getHint() {
        return gameBoard.getHint();
    }

    public Team getRedTeam() {
        return redTeam;
    }

    public Team getBlueTeam() {
        return blueTeam;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }
}
