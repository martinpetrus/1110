package zone.iioi.core;

import com.lmax.disruptor.RingBuffer;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

public class MidiInListener implements Receiver {
    private final RingBuffer<Event> ringBuffer;
    private final Transmitter midiIn;

    private MidiInListener(RingBuffer<Event> ringBuffer, Transmitter midiIn) {
        this.ringBuffer = ringBuffer;
        this.midiIn = midiIn;
    }

    public static void register(RingBuffer<Event> ringBuffer, Transmitter midiIn) {
        midiIn.setReceiver(new MidiInListener(ringBuffer, midiIn));
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        publishEvent(message);
    }

    private void publishEvent(MidiMessage message) {
        long sequence = ringBuffer.next();
        try {
            Event event = ringBuffer.get(sequence);
            event.update(midiIn, message);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    @Override
    public void close() {
        midiIn.close();
    }

}