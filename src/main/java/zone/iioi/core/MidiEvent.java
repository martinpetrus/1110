package zone.iioi.core;

import javax.sound.midi.MidiDevice;

/**
 *  @author petrmar
 */
public class MidiEvent {
    MidiDevice midiDevice;
    javax.sound.midi.MidiMessage event;

    public MidiDevice getDevice() {
        return midiDevice;
    }

    public javax.sound.midi.MidiMessage getMessage() {
        return event;
    }

    public void update(MidiDevice midiDevice, javax.sound.midi.MidiMessage event) {
        this.midiDevice = midiDevice;
        this.event = event;
    }
}
