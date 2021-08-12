package swen225.murdermadness;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface Subject {
    enum Event { LEFT,RIGHT,UP,DOWN,SETUP,UPDATE_BOARD,SET_STEPS,SHOW_HAND,RESET }

    void notify(Event event);

    void notify(Object obj, Event event);

    void notify(Map<String, String> collection, BufferedImage img, Subject.Event event);
}
