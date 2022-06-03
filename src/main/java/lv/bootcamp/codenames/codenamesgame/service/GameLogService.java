package lv.bootcamp.codenames.codenamesgame.service;

import lv.bootcamp.codenames.codenamesgame.model.chat.LogMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameLogService {

    private List<LogMessage> logMessageList;

    public GameLogService() {
        this.logMessageList = new ArrayList<>();
    }

    public void log(LogMessage logMessage) {
        logMessageList.add(logMessage);
    }

    public void clearLog() {
        logMessageList.clear();
    }

    public List<LogMessage> getHistory() {
        return logMessageList;
    }
}
