package lv.bootcamp.codenames.codenamesgame.controller;

import lv.bootcamp.codenames.codenamesgame.model.chat.LogMessage;
import lv.bootcamp.codenames.codenamesgame.model.chat.Message;
import lv.bootcamp.codenames.codenamesgame.model.chat.OutputMessage;
import lv.bootcamp.codenames.codenamesgame.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    private ChatService chatService;
    public static final String LOG_TOPIC_PATH = "/topic/log";

    public WebsocketController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        return chatService.getOutputMessage(message);
    }

    @MessageMapping("/log")
    @SendTo(LOG_TOPIC_PATH)
    public LogMessage send(LogMessage message) throws Exception {
        return new LogMessage(message.getText());
    }

}
