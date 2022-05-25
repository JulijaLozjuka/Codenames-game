package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import lv.bootcamp.codenames.codenamesgame.model.Team;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.GameBoard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameEngine {

    private Team redTeam;
    private Team blueTeam;
    private GameBoard gameBoard;
    private String hint;
    private final CardGenerator cardGenerator;

    public GameEngine(CardGenerator cardGenerator) {
        this.redTeam = new Team();
        this.blueTeam = new Team();
        this.cardGenerator = cardGenerator;
    }

    public void addPlayer(Player player) {
        if (redTeam.isTeamFull()&& blueTeam.isTeamFull()){
            return;
        }
        if (!redTeam.isTeamFull()){
            redTeam.addPlayer(player);
        }
        else {
            blueTeam.addPlayer(player);
        }
        if (getPlayerCount()==4){
            startGame();
        }
    }

    private void startGame() {
        gameBoard = new GameBoard();
        List<Card> gameCards = cardGenerator.generateCards();
        gameBoard.setGameCards(gameCards);

    }

    public int getPlayerCount(){
        return redTeam.getPlayerCount()+ blueTeam.getPlayerCount();
    }
    public List<Player> getPlayerList(){
        return Stream.of(redTeam.getOperative(), redTeam.getSpymaster(), blueTeam.getOperative(), blueTeam.getSpymaster())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }


}
