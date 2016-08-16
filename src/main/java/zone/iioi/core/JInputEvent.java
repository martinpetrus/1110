package zone.iioi.core;

import net.java.games.input.Controller;

public class JInputEvent {

    Controller controller;
    Event event;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
