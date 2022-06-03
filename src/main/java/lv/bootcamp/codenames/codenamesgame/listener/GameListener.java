package lv.bootcamp.codenames.codenamesgame.listener;

import lv.bootcamp.codenames.codenamesgame.model.events.ResetGameEvent;
import lv.bootcamp.codenames.codenamesgame.service.GameLogService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GameListener {

    private GameLogService gameLogService;

    public GameListener(GameLogService gameLogService) {
        this.gameLogService = gameLogService;
    }

    @EventListener
    public void reset(ResetGameEvent event){
        gameLogService.clearLog();
    }
}
