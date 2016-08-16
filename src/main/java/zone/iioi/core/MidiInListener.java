package zone.iioi.core;

import com.lmax.disruptor.RingBuffer;

import javax.sound.midi.*;

public class MidiInListener implements Receiver {
    private final RingBuffer<Event> ringBuffer;
    private final MidiDevice midiIn;

    public MidiInListener(RingBuffer<Event> ringBuffer, MidiDevice midiIn) throws Exception {
        this.ringBuffer = ringBuffer;
        this.midiIn = midiIn;
        this.midiIn.getTransmitter().setReceiver(this);
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {

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

    }

}