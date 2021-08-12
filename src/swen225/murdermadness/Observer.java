package swen225.murdermadness;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface Observer {

    void onNotify(Subject.Event event);

    void onNotify(Object obj, Subject.Event event);

    void onNotify(Map<String, String> collection, BufferedImage img, Subject.Event event);
}
