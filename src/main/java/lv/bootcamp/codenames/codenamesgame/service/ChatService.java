package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.chat.Message;
import lv.bootcamp.codenames.codenamesgame.model.chat.OutputMessage;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChatService {

    private List<OutputMessage> msgHistory;

    public ChatService() {
        msgHistory = new ArrayList<>();
    }

    public OutputMessage getOutputMessage(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        OutputMessage outputMessage = new OutputMessage(message.getFrom(), message.getText(), time);
        msgHistory.add(outputMessage);
        return outputMessage;
    }

    public List<OutputMessage> getHisotry() {
        return msgHistory;
    }
}
