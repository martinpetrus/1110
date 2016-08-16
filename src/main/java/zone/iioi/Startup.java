package zone.iioi;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import zone.iioi.core.Event;
import zone.iioi.core.EventHandler;
import zone.iioi.core.JInputPoller;

public class Startup {

    public static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        Disruptor<Event> disruptor = new Disruptor<>(Event::new, BUFFER_SIZE, threadFactory);
        disruptor.handleEventsWith(new EventHandler());
        disruptor.start();

        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();
        JInputPoller jInputPoller = new JInputPoller(ringBuffer, controllers);
        threadFactory.newThread(jInputPoller::poll).start();

    }
}
