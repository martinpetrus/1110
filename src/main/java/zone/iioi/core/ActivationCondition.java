package zone.iioi.core;

import javax.sound.midi.Transmitter;
import java.util.function.Predicate;

import net.java.games.input.Component;
import net.java.games.input.Controller;

public class ActivationCondition {

    private boolean satisfied;
    private final Predicate<Event> satisfactionPredicate;
    private final Predicate<Event> dissatisfactionPredicate;

    private ActivationCondition(Predicate<Event> satisfactionPredicate, Predicate<Event> dissatisfactionPredicate) {
        this.satisfactionPredicate = satisfactionPredicate;
        this.dissatisfactionPredicate = dissatisfactionPredicate;
    }

    public boolean test(Event event) {
        satisfied = satisfied
                ? !dissatisfactionPredicate.test(event)
                : satisfactionPredicate.test(event);
        return satisfied;
    }

    private static class Builder implements JInputConditionBuilder, MidiInConditionBuilder, Toggle, Build {

        Controller controller;
        Component component;
        float value;



        boolean toggle;




    }

    private interface JInputConditionBuilder {
        Toggle valueIsEqualTo(float value);
        Toggle valueIsGreaterThan(float value);
        Toggle valueIsGreaterOrEqualTo(float value);
        Toggle valueIsLessThan(float value);
        Toggle valueIsLessOrEqualTo(float value);
    }

    private interface Toggle extends Build {
        Build isToggleType();
    }

    private interface Build {
        ActivationCondition build();
    }

    private enum CompareType {
        EQ,
        GT,
        GTE,
        LT,
        LTE
    }


}
