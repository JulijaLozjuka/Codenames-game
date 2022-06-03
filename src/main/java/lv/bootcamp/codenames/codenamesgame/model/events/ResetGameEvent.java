package lv.bootcamp.codenames.codenamesgame.model.events;

import org.springframework.context.ApplicationEvent;

public class ResetGameEvent extends ApplicationEvent {
    public ResetGameEvent(Object source) {
        super(source);
    }
}
