import data.GameInfoChangeListener;
import view.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by minchul on 2017-06-08.
 */
public class MyView implements GameInfoChangeListener {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    private JPanel panel;
    private MyPanel myPanel;
    private JLabel timeLabel;
    private JLabel levelLabel;

    public MyView() {

    }


    private void createUIComponents() {
        try {
            myPanel = new MyPanel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createAndShowGui() throws IOException {
        MyView myView = new MyView();

        JFrame frame = new JFrame("BulletExample");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // add it to the JFrame
        frame.setContentPane(myView.panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        myView.timeLabel.setText("60");
        myView.timeLabel.setText("1");
        myView.myPanel.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGui();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onChange(int time, int level) {
        timeLabel.setText(String.valueOf(time));
        levelLabel.setText(String.valueOf(level));

    }

}
