package zone.iioi.core;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Transmitter;

/**
 *  @author petrmar
 */
public class MidiEvent {

    private Transmitter midiDevice;
    private MidiMessage event;

    public Transmitter getDevice() {
        return midiDevice;
    }

    public MidiMessage getMessage() {
        return event;
    }

    public void update(Transmitter midiDevice, MidiMessage event) {
        this.midiDevice = midiDevice;
        this.event = event;
    }
}
