package zone.iioi.core;

import net.java.games.input.Controller;

public class Event {

    EventType type;

    private InternalTask internalTask;
    private JInputEvent jInputEvent;
    private MidiEvent midiEvent;

    public void update(InternalTask task) {
        type = EventType.INTERNAL;
        internalTask = task;
    }

    public void update(Controller controller, net.java.games.input.Event event) {
        type = EventType.JINPUT;
        jInputEvent.update(controller, event);
    }

    public void update(MidiEvent event) {
        type = EventType.MIDI;
        midiEvent.update(event);
    }

    public EventType getType() {
        return type;
    }

    public InternalTask getInternalTask() {
        return internalTask;
    }

    public JInputEvent getjInputEvent() {
        return jInputEvent;
    }

    public MidiEvent getMidiEvent() {
        return midiEvent;
    }
}
