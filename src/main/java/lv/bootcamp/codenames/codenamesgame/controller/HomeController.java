package lv.bootcamp.codenames.codenamesgame.controller;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import lv.bootcamp.codenames.codenamesgame.model.PlayerTurnStatus;
import lv.bootcamp.codenames.codenamesgame.model.TurnData;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Color;
import lv.bootcamp.codenames.codenamesgame.service.GameEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/codenames")
public class HomeController {

    private final GameEngine gameEngine;

    public HomeController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @GetMapping("/user-name-input")
    public String getUserNameInput(ModelMap modelMap) {
        Player player = new Player();
        modelMap.addAttribute("player", player);
        return "userNameInput";
    }

    @PostMapping("/save-player")
    public String saveUser(Player player) {
        gameEngine.addPlayer(player);
        return "redirect:/codenames/waiting/" + player.getName();
    }

    @GetMapping("/waiting/{playerName}")
    public String getWaitingPage(@PathVariable(value = "playerName") String playerName, ModelMap modelMap) {
        modelMap.addAttribute("player", playerName);
        String waitMsg = "Waiting for other players to connect...";
        modelMap.addAttribute("waitMsg", waitMsg);
        modelMap.addAttribute("showEnterGameButton", gameEngine.allPlayersReady());
        modelMap.addAttribute("playerList", gameEngine.getPlayerList());
        return "waiting";
    }

    @GetMapping("/main-page/{playerName}")
    public String getMainPage(@PathVariable(value = "playerName") String playerName, ModelMap modelMap) {
        modelMap.addAttribute("player", playerName);
        List<Card> cardList = gameEngine.getGameBoard();
        modelMap.addAttribute("cardList", cardList);
        PlayerTurnStatus playerTurnStatus = gameEngine.checkPlayer(playerName);
        modelMap.addAttribute("showSpymasterElements", playerTurnStatus.isSpymaster());
        modelMap.addAttribute("showOperativeElements", !playerTurnStatus.isSpymaster());
        modelMap.addAttribute("showActivePlayerElements", playerTurnStatus.isPlayersTurn());

        Color activePlayerColor = gameEngine.getPLayerColor(gameEngine.getActivePlayer().getName());
        Color thisPlayerColor = gameEngine.getPLayerColor(playerName);
        boolean isActivePlayerFromYourTeam = activePlayerColor==thisPlayerColor;
        boolean isActivePlayerSpymaster = gameEngine.checkPlayer(gameEngine.getActivePlayer().getName()).isSpymaster();
        modelMap.addAttribute("isActivePlayerFromYourTeam",isActivePlayerFromYourTeam);
        modelMap.addAttribute("isActivePlayerSpymaster",isActivePlayerSpymaster);

        modelMap.addAttribute("activePlayerName",gameEngine.getActivePlayer().getName());

        modelMap.addAttribute("hint", gameEngine.getHint());
        modelMap.addAttribute("turnData", new TurnData());
        modelMap.addAttribute("redTeam", gameEngine.getRedTeam());
        modelMap.addAttribute("blueTeam", gameEngine.getBlueTeam());
        modelMap.addAttribute("gameEnd", gameEngine.isGameEnd());
        modelMap.addAttribute("winningTeam", gameEngine.getWinningTeam());

        int cssClassNumber = (int )(Math.random()*4+1);
        modelMap.addAttribute("cssClassNumber",cssClassNumber);

        return "mainPage";
    }

    @PostMapping("/player/move/{playerName}")
    public String spymasterMove(@PathVariable(value = "playerName") String playerName, TurnData turnData) {
        gameEngine.playerMove(playerName, turnData);
        return "redirect:/codenames/main-page/" + playerName;
    }

    @GetMapping("/restart/{playerName}")
    public String resetGame(@PathVariable(value = "playerName") String playerName, ModelMap modelMap) {
        gameEngine.restart();
        return "redirect:/codenames/main-page/" + playerName;
    }

}
