package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    GameEngine gameEngine = new GameEngine(null);

    @Test
    void playerCountTest() {
        gameEngine.addPlayer(new Player());
        assertEquals(1,gameEngine.getPlayerCount());
        gameEngine.addPlayer(new Player());
        gameEngine.addPlayer(new Player());
        gameEngine.addPlayer(new Player());
        assertEquals(4,gameEngine.getPlayerCount());
        //checking if we can add fifth player
        gameEngine.addPlayer(new Player());
        assertEquals(4,gameEngine.getPlayerCount());
    }

}