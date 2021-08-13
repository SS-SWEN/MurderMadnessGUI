package swen225.murdermadness;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public final class GrabAsset {
    public static BufferedImage grabAsset(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
}
