package zone.iioi.core;

import javax.sound.midi.MidiEvent;

public class Event {
    EventType type;

    private MidiEvent midiEvent;

    public static enum EventType {
        INTERNAL,
        JOYSTICK,
        MIDI,
        API
    }
}
