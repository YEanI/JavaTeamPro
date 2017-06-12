package data;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by minchul on 2017-06-08.
 */

@Data
public class DrawingObject {
    Image image;
    Point point;

    public DrawingObject(){
        point = new Point(0, 0);
    }

    public void setImage(String filePath, int size){
        URL imageURL = DrawingObject.class.getResource(filePath);
        try {
            image = ImageIO.read(imageURL).getScaledInstance(size, size, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
            image = null;
        }
    }

    public int getWidth(){
        if(image != null){
            return image.getWidth(null);
        }
        return -1;
    }

    public int getHeight(){
        if (image != null) {
            return image.getHeight(null);
        }
        return -1;
    }
}
