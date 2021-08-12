package swen225.murdermadness;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface Observer {

    /**
     * Update in response to an event,
     */
    void update(Subject.Event event);

    /**
     * Update in response to an event, using passed Object passed as required
     */
    void update(Object obj, Subject.Event event);

    /**
     * Retrieve object an as required
     */
    Object retrieve(Subject.Event obj);
}
