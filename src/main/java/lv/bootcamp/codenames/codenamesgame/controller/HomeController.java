package lv.bootcamp.codenames.codenamesgame.controller;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import lv.bootcamp.codenames.codenamesgame.service.GameEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getUserNameInput(ModelMap modelMap){
        Player player = new Player();
        modelMap.addAttribute("player", player);
        modelMap.addAttribute("playerList",gameEngine.getPlayerList());
        return "userNameInput";
    }

    @PostMapping("/save-player")
    public String saveUser(Player player) {
        gameEngine.addPlayer(player);
        return "mainPageTest";
    }


}