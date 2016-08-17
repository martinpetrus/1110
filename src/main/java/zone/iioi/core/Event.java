package zone.iioi.core;

import net.java.games.input.Controller;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Transmitter;
import java.util.Set;

public class Event {

    EventType type;

    private InternalTask internalTask;
    private JInputEvent jInputEvent;
    private MidiEvent midiEvent;

    private Set<Action> triggeredActions;

    public void update(InternalTask task) {
        triggeredActions.clear();
        type = EventType.INTERNAL;
        internalTask = task;
    }

    public void update(Controller controller, net.java.games.input.Event event) {
        triggeredActions.clear();
        type = EventType.JINPUT;
        jInputEvent.update(controller, event);
    }

    public void update(Transmitter midiDevice, MidiMessage event) {
        triggeredActions.clear();
        type = EventType.MIDI;
        midiEvent.update(midiDevice, event);
    }

    public EventType getType() {
        return type;
    }

    public InternalTask getInternalTask() {
        return internalTask;
    }

    public JInputEvent getJInputEvent() {
        return jInputEvent;
    }

    public MidiEvent getMidiEvent() {
        return midiEvent;
    }
}
