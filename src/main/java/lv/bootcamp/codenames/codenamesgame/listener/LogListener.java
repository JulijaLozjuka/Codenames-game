package lv.bootcamp.codenames.codenamesgame.listener;

import lv.bootcamp.codenames.codenamesgame.model.chat.LogMessage;
import lv.bootcamp.codenames.codenamesgame.model.events.ActionLoggedEvent;
import lv.bootcamp.codenames.codenamesgame.service.GameLogService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static lv.bootcamp.codenames.codenamesgame.controller.WebsocketController.LOG_TOPIC_PATH;

@Component
public class LogListener {

    private SimpMessagingTemplate simpMessagingTemplate;
    private GameLogService gameLogService;

    public LogListener(SimpMessagingTemplate simpMessagingTemplate, GameLogService gameLogService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.gameLogService = gameLogService;
    }

    @EventListener
    void sendMsgEvent(ActionLoggedEvent emailEvent) {
        LogMessage logMessage = new LogMessage(emailEvent.getText());
        gameLogService.log(logMessage);
        simpMessagingTemplate.convertAndSend(LOG_TOPIC_PATH, logMessage);
    }
}
