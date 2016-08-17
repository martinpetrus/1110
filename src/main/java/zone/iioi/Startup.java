package zone.iioi;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import zone.iioi.core.Event;
import zone.iioi.core.EventHandler;
import zone.iioi.core.JInputPoller;
import zone.iioi.core.MidiInListener;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

public class Startup {

    public static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) throws Exception {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        Disruptor<Event> disruptor = new Disruptor<>(Event::new, BUFFER_SIZE, threadFactory);
        disruptor.handleEventsWith(new EventHandler());
        disruptor.start();

        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();
        JInputPoller jInputPoller = new JInputPoller(ringBuffer, controllers);
        threadFactory.newThread(jInputPoller::poll).start();
        getMidiDeviceStream()
                .flatMap(d -> d.getTransmitters().stream())
                .forEach(t -> MidiInListener.register(ringBuffer, t));

    }

    private static Stream<MidiDevice> getMidiDeviceStream() {
        return Arrays.stream(MidiSystem.getMidiDeviceInfo())
                .map(info -> {
                    try {
                        MidiDevice midiDevice = MidiSystem.getMidiDevice(info);
                        if (!midiDevice.isOpen()) {
                            midiDevice.open();
                        }
                        return midiDevice;
                    } catch (MidiUnavailableException e) {
                        System.out.println("Error: Device" + info.getName() + " not available!");
                    }
                    return null;
                })
                .filter(Objects::nonNull);
    }
}
