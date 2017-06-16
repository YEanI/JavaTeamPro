package view;

import app.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import static app.GameConstants.SCREEN_WIDTH;

/**
 * Created by minchul on 2017-06-12.
 */
public class HelpView extends BaseView {
    private JPanel panel;
    private JLabel label1;
    private JLabel label2;


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
        Image scaleImage2 = getScaleImage(icon2.getImage(), 350, 350);
        icon2.setImage(scaleImage2);
        label1.setIcon(icon2);
        label1.setName(null);
        label1.setText(null);

        label2 = new JLabel();
        URL imageURL = HelpView.class.getResource("/images/Class.png");
        ImageIcon icon = new ImageIcon(imageURL);
        Image scaleImage = getScaleImage(icon.getImage(), 350, 200);
        icon.setImage(scaleImage);
        label2.setIcon(icon);
        label2.setName(null);
        label2.setText(null);


    }

    /**
     * @param image  원본 이미지
     * @param width  우리가 원하는 너비
     * @param height 우리가 원하는 높이
     * @return 우리가 원하는 사이즈로 새로 그려진 원본 이미지
     */
    private Image getScaleImage(Image image, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();

        return resizedImg;
    }

    @Override
    public void onSwiched() {

    }
}
