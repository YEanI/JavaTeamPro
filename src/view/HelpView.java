package view;

import util.GameConstants;
import util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import static util.GameConstants.SCREEN_WIDTH;

/**
 * Created by minchul on 2017-06-12.
 */
public class HelpView extends BaseView {
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;


    @Override
    public JPanel getContentPanel() {

        return panel;
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));

        label1 = new JLabel();
        URL image = HelpView.class.getResource("/images/bubble1.png");
        ImageIcon icon2 = new ImageIcon(image);
        Image scaleImage2 = ImageUtil.getScaleImage(icon2.getImage(), 350, 350);
        icon2.setImage(scaleImage2);
        label1.setIcon(icon2);

        label2 = new JLabel();
        URL imageURL = HelpView.class.getResource("/images/Class.png");
        ImageIcon icon = new ImageIcon(imageURL);
        Image scaleImage = ImageUtil.getScaleImage(icon.getImage(), 350, 200);
        icon.setImage(scaleImage);
        label2.setIcon(icon);

        button1 = new JButton();
        button1.setText("BACK");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startView(MainView.class);
            }
        });

    }


    @Override
    public void onViewChanged() {

    }
}
