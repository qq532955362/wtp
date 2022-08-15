package org.example.user.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class PrintHelloEvent  extends ApplicationEvent {

    private Long currentMillis;

    public PrintHelloEvent(Object source,Long currentMillis) {
        super(source);
        this.currentMillis = currentMillis;
    }
}
