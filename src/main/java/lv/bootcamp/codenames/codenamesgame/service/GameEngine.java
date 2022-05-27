package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import lv.bootcamp.codenames.codenamesgame.model.PlayerTurnStatus;
import lv.bootcamp.codenames.codenamesgame.model.Team;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.GameBoard;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Hint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameEngine {

    private final static int FULL_TEAMS = 4;
    
    private Player activePlayer;

    private Team redTeam;
    private Team blueTeam;
    private GameBoard gameBoard;
    private final CardGenerator cardGenerator;


    public GameEngine(CardGenerator cardGenerator) {
        this.redTeam = new Team();
        this.blueTeam = new Team();
        this.cardGenerator = cardGenerator;
    }

    public void addPlayer(Player player) {
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
        gameBoard = new GameBoard();
        List<Card> gameCards = cardGenerator.generateCards();
        gameBoard.setGameCards(gameCards);
        activePlayer = redTeam.getSpymaster();
    }
    public void passTheTurnToTheNextPlayer(){
        if(activePlayer.getName().equals(redTeam.getSpymaster().getName())){
            activePlayer = redTeam.getOperative();
        }

        if(activePlayer.getName().equals(redTeam.getOperative().getName())){
            activePlayer = blueTeam.getSpymaster();
        }

        if(activePlayer.getName().equals(blueTeam.getSpymaster().getName())){
            activePlayer = blueTeam.getOperative();
        }

        if(activePlayer.getName().equals(blueTeam.getOperative().getName())){
            activePlayer = redTeam.getSpymaster();
        }
    }
    public PlayerTurnStatus checkPlayer(String playerName) {
        PlayerTurnStatus result = new PlayerTurnStatus();

        if(activePlayer.getName().equals(playerName)){
            result.setPlayersTurn(true);
        }

        if(redTeam.getSpymaster().getName().equals(playerName)
                || blueTeam.getSpymaster().getName().equals(playerName) ){
            result.setSpymaster(true);
        }

        return result;

    }
    public boolean checkIfWinnerFound() {
        return gameBoard.checkAllRedCardsRevealed() || gameBoard.checkAllBlueCardsRevealed() || gameBoard.checkBlackCardRevealed();
    }

    public int getPlayerCount() {
        return redTeam.getPlayerCount() + blueTeam.getPlayerCount();
    }

    public List<Player> getPlayerList() {
        return Stream.of(redTeam.getOperative(), redTeam.getSpymaster(), blueTeam.getOperative(), blueTeam.getSpymaster())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    public void setHint(Hint hint){
        gameBoard.setHint(hint);
    }
    public Hint getHint(){
        return gameBoard.getHint();
    }



    public Team getRedTeam() {
        return redTeam;
    }

    public void setRedTeam(Team redTeam) {
        this.redTeam = redTeam;
    }

    public Team getBlueTeam() {
        return blueTeam;
    }

    public void setBlueTeam(Team blueTeam) {
        this.blueTeam = blueTeam;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean allPlayersReady() {
        return getPlayerCount() == FULL_TEAMS;
    }


    

}
