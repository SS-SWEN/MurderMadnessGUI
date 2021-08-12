package swen225.murdermadness;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface Subject {
    /**
     * List of possible events that an observer can respond to
     */
    enum Event { LEFT,RIGHT,UP,DOWN,SETUP,UPDATE_BOARD,SET_STEPS,RESET,
        UPDATE_ELIM,GATHER_CARDS,CHOOSE,GUESS,ACCUSE,SET_CHOSEN,CURRENT_PLAYER}

    /**
     * Notify the observers of an event
     */
    void notify(Event event);

    /**
     * Notify the Observer of an event, pass an Object that is required to
     * complete action
     */
    void notify(Object obj, Event event);

    /**
     * Request for an object from an Observer
     */
    Object request(Event object);
}
