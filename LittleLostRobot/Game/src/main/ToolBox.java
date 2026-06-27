package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ToolBox {

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {

            BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); // BufferedImage is first instantiated
            Graphics2D g2 = scaledImage.createGraphics(); // This saves whatever g2 draws in scaledImage
            g2.drawImage(original, 0, 0, width, height, null); // This draws the grass (tile[0]) into the scaledImage variable Graphics 2D is now linked to
            g2.dispose();
            return scaledImage;
    }
}
