package main.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel {

    private static final int WIDTH = 28;
    private static final int HEIGHT = 28;

    private static final double SCALE = 12.0D;
    private static final int WINDOW_WIDTH = WIDTH*(int)SCALE;
    private static final int WINDOW_HEIGHT = HEIGHT*(int)SCALE;
    private static final int DRAW_SIZE = 3;
    private BufferedImage image;
    private int xPos = WIDTH*(int)SCALE+1;
    private int yPos = HEIGHT*(int)SCALE+1;
    private int hardness = 40;

    public PaintPanel() {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.initMouseListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = this.image.createGraphics();
        g2.setColor(new Color(255, 255, 255, this.hardness));
        g2.fillOval((int)(xPos/SCALE)-1, (int)(yPos/SCALE)-1, DRAW_SIZE, DRAW_SIZE);
        g.drawImage(this.image, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
    }

    public void updateHardness(int hardness) {
        this.hardness = hardness;
    }

    public void clearImage() {
        this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.xPos = WIDTH*(int)SCALE+1;
        this.yPos = HEIGHT*(int)SCALE+1;
        this.repaint();
    }

    public double[] getPixels() {
        int[] pixels = new int[WIDTH*HEIGHT];
        int count = 0;
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                pixels[count++] = this.image.getRGB(x, y);
            }
        }

        double[] testData = new double[WIDTH*HEIGHT];

        for(int i = 0; i < pixels.length; i++) {
            Color color = new Color(pixels[i], true);
            double lum = Math.round(0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue());
            testData[i] = lum;
        }

        return testData;
    }

    private void initMouseListeners() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xPos = e.getX();
                yPos = e.getY();
                SwingUtilities.invokeLater(PaintPanel.this::repaint);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xPos = e.getX();
                yPos = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xPos = e.getX();
                yPos = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                xPos = e.getX();
                yPos = e.getY();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

}
