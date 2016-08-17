package zone.iioi.core;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Context {

    private int bpm = 120;
    private int signature = 96;
    private BitSet scale = new BitSet(12);




    private List<ActionDefinition> actions = new ArrayList<>();

    public Set<Action> getTriggeredActions(Event event)  {
        return Collections.emptySet();
    }


}
