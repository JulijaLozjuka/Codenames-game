package lv.bootcamp.codenames.codenamesgame.model.events;

import org.springframework.context.ApplicationEvent;

public class ActionLoggedEvent extends ApplicationEvent {

    private String text;

    public ActionLoggedEvent(Object source, String text) {
        super(source);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
