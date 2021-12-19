package tinyML.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class ImgUtils extends JPanel {
    // fields
    private static List<ImgUtils> list = new LinkedList<>();
    private JFrame frame;
    private BufferedImage image;
    JTextField textField;

    // constructor
    private ImgUtils(String title) {
        super();
        createFrame(title);
        createLabel();
    }

    // methods
    public static ImgUtils getInstance(String title) {
        for (ImgUtils element : list) {
            if (element.frame.getTitle().contentEquals(title)) {
                return element;
            }
        }
        ImgUtils result = new ImgUtils(title);
        list.add(result);
        return result;
    }

    public static boolean hasInstance(String title) {
        for (ImgUtils element : list) {
            if (element.frame.getTitle().contentEquals(title)) {
                return true;
            }
        }
        return false;
    }

    private void setImage(BufferedImage image) {
        this.image = image;
    }

    private void createFrame(String title) {
        this.frame = new JFrame(title);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this, BorderLayout.CENTER);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLocation(0,0);
        this.frame.setVisible(true);
    }

    private void createLabel() {
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD,20));
        textField.setEnabled(false);
        this.frame.add(textField, BorderLayout.SOUTH);
    }

    private void resizeFrame() {
        this.frame.setSize(image.getWidth() * 10 + 16, image.getHeight() * 10 + 60);
    }

    public BufferedImage createByteGrayImage(double[] data, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(j, i, (byte) data[i * width + j]);
            }
        }
        return image;
    }

    public void showCostImage(double cost) {
        int width = 45;
        int height = 45;
        double[] data = new double[45 * 45];
        this.image = this.createByteGrayImage(data, width, height);
        this.drawCost(this.image.getGraphics(), cost);
        this.repaint();
    }

    private void drawCost(Graphics g, double cost) {
        g.setColor(Color.white);
        int x = (int) (cost * 30);
        g.drawOval(x, x, 1, 1);
    }

    public void showImage(BufferedImage image) {
        this.setImage(image);
        this.resizeFrame();
        this.repaint();
    }

    public void showImage(BufferedImage image, String label) {
        //
        textField.setText("predict: " + label);
        //
        this.setImage(image);
        this.resizeFrame();
        this.repaint();
    }

    public boolean getVisible() {
        return this.frame.isVisible();
    }

    public void setVisible(boolean bool) {
        this.frame.setVisible(bool);
    }

    public void showImage(double[] data, int width, int height) {
        this.showImage(this.createByteGrayImage(data, width, height));
    }

    public void showImage(double[] data, int width, int height, String label) {
        this.showImage(this.createByteGrayImage(data, width, height), label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (image != null) {
            g2d.drawImage(image, 0, 0, image.getWidth() * 10, image.getHeight() * 10, this);
        }
        g2d.dispose();
    }

    // static
    public static double[] convertImageToArray(BufferedImage bufferedImage, double divisor) {
        double[] result = new double[bufferedImage.getWidth() * bufferedImage.getHeight()];

        for (int i = 0; i < result.length; i++) {
            if (0 < divisor && divisor < 255) {
                result[i] = bufferedImage.getData().getDataBuffer().getElem(i) / divisor;
            } else {
                result[i] = bufferedImage.getData().getDataBuffer().getElem(i);
            }
        }

        return result;
    }

    public static BufferedImage scaleImage(BufferedImage inputImage, int width, int height) {
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(inputImage, 0, 0, width, height, 0, 0, inputImage.getWidth(), inputImage.getHeight(), null);
        g2d.dispose();
        return outputImage;
    }
}
