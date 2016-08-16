package zone.iioi;

import com.lmax.disruptor.dsl.Disruptor;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Startup {

    public static void main(String[] args) {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        System.out.println(controllers);

    }
}
