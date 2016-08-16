package zone.iioi.core;

import javax.sound.midi.MidiDevice;

/**
 *  @author petrmar
 */
public class MidiEvent {
    MidiDevice midiDevice;
    javax.sound.midi.MidiEvent event;

    public MidiDevice getDevice() {
        return midiDevice;
    }

    public javax.sound.midi.MidiEvent getEvent() {
        return event;
    }

    public void update(MidiEvent event) {
        midiDevice = event.getDevice();
        this.event = event.getEvent();
    }
}
