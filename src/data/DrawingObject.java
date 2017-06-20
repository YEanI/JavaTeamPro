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
    final Point point;

    public DrawingObject(){
        image = null;
        point = new Point(0, 0);
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
