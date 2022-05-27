package lv.bootcamp.codenames.codenamesgame.controller;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import lv.bootcamp.codenames.codenamesgame.model.PlayerTurnStatus;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Card;
import lv.bootcamp.codenames.codenamesgame.model.gameelements.Hint;
import lv.bootcamp.codenames.codenamesgame.service.GameEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
        modelMap.addAttribute("playerList", gameEngine.getPlayerList());
        return "userNameInput";
    }

    @PostMapping("/save-player")
    public String saveUser(Player player) {
        gameEngine.addPlayer(player);
        return "redirect:/codenames/waiting/" + player.getName();
    }

    @GetMapping("/waiting/{playerName}")
    public String getWaitingPage(@PathVariable(value = "playerName") String playerName, ModelMap modelmap) {
        modelmap.addAttribute("player", playerName);
        String waitMsg = "Waiting for other players to connect...";
        modelmap.addAttribute("waitMsg", waitMsg);
        modelmap.addAttribute("showEnterGameButton",gameEngine.allPlayersReady());

        return "waiting";
    }
    @GetMapping("/main-page/{playerName}")
    public String getMainPage(@PathVariable(value = "playerName") String playerName, ModelMap modelMap){
        modelMap.addAttribute("player",playerName);
        List<Card> cardList = gameEngine.getGameBoard().getGameCards();
        List<List<Card>> cardRows = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
         cardRows.add(cardList.subList(i*5,(i+1)*5));
        }
        modelMap.addAttribute("cardRows",cardRows);
        PlayerTurnStatus playerTurnStatus = gameEngine.checkPlayer(playerName);
        modelMap.addAttribute("showSpymasterElements", playerTurnStatus.isSpymaster());
        modelMap.addAttribute("showActivePlayerElements", playerTurnStatus.isPlayersTurn());
        modelMap.addAttribute("hint",gameEngine.getHint());
        return "mainPage";
    }
    @PostMapping("/spymaster/move/{playerName}")
    public String spymasterMove(@PathVariable(value = "playerName") String playerName, Hint hint){
        gameEngine.setHint(hint);
        return "redirect: /codenames/main-page/" + playerName;
    }
    @PostMapping("/operative/move/{playerName}")
    public String operativeMove(@PathVariable(value = "playerName") String playerName, String word){
        List<Card> cardList = gameEngine.getGameBoard().getGameCards();
        for (Card card:cardList){
            if (word.equals(card.getText())){
                card.setRevealed(true);
            }
        }
        return "redirect: /codenames/main-page/" + playerName;
    }







}
