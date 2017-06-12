package viewcomponent;

import data.DrawingObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
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

    public void removeDrawingObject(Collection<DrawingObject> objects) {
        drawingObjectList.removeAll(objects);
    }

    public void removeDrawingObject(DrawingObject object) {
        drawingObjectList.remove(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D graphics2D = (Graphics2D) g;

        drawingObjectList.forEach(object -> graphics2D.drawImage(object.getImage(), object.getPoint().x, object.getPoint().y, GamePanel.this));
        Toolkit.getDefaultToolkit().sync();
    }


}