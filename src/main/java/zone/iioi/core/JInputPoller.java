package zone.iioi.core;

import com.lmax.disruptor.RingBuffer;
import net.java.games.input.Controller;

public class JInputPoller {

    private final RingBuffer<Event> ringBuffer;
    private final Controller[] controllers;
    private boolean shutdown;

    public JInputPoller(RingBuffer<Event> ringBuffer, Controller[] controllers) {
        this.ringBuffer = ringBuffer;
        this.controllers = controllers;
    }

    public void poll() {
        net.java.games.input.Event jInputEvent = new net.java.games.input.Event();
        while (!shutdown) {
            for (Controller controller : controllers) {
                controller.poll();
                while (controller.getEventQueue().getNextEvent(jInputEvent)) {
                    publishEvent(controller, jInputEvent);
                }
            }
        }
    }

    private void publishEvent(Controller controller, net.java.games.input.Event jInputEvent) {
        long sequence = ringBuffer.next();
        try {
            Event event = ringBuffer.get(sequence);
            event.update(controller, jInputEvent);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public void shutdown() {
        shutdown = true;
    }
}
