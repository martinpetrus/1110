package zone.iioi.core;

import net.java.games.input.*;
import net.java.games.input.Event;

public class JInputEvent {

    Controller controller;
    net.java.games.input.Event event;

    public Controller getController() {
        return controller;
    }

    public net.java.games.input.Event getEvent() {
        return event;
    }

    public void update(Controller controller, Event event) {
        this.controller = controller;
        this.event.set(event);
    }
}
