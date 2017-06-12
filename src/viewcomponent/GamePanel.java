package viewcomponent;

import data.DrawingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel {

    private List<DrawingObject> drawingObjectList;

    public GamePanel() {
        super();
        drawingObjectList = new ArrayList<>();
    }

    public void addDrawingObject(DrawingObject object) {
        drawingObjectList.add(object);
    }

    public void removeDrawingObject(List<DrawingObject> objectList) {
        drawingObjectList.removeAll(objectList);
    }

    public void removeDrawingObject(DrawingObject object) {
        drawingObjectList.remove(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawingObject object : drawingObjectList) {
            g.drawImage(object.getImage(), object.getPoint().x, object.getPoint().y, this);
        }
        Toolkit.getDefaultToolkit().sync();
    }



}