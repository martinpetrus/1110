package zone.iioi.core;

import javax.sound.midi.MidiEvent;

/**
 * Created by Petrus on 23.07.2016.
 */
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
