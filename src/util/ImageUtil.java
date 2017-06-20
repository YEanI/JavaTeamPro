package util;

import data.DrawingObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by minchul on 2017-06-20.
 */
public class ImageUtil {
    public static Image loadImage(String filePath, int size){
        final URL imageURL = DrawingObject.class.getResource(filePath);
        Image image;
        try {
            image = ImageIO.read(imageURL).getScaledInstance(size, size, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
            image = null;
        }
        return image;
    }
    public static Image getScaleImage(Image image, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }
}
