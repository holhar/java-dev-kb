package de.holhar.java_dev_kb.designpatterns.structural.flyweight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingClient extends JFrame {

    private static final long serialVersionUID = 1L;
    private final int width;
    private final int height;

    private static final FlyweightShapeFactory.ShapeType shapes[] = { FlyweightShapeFactory.ShapeType.LINE,
            FlyweightShapeFactory.ShapeType.OVAL_FILL, FlyweightShapeFactory.ShapeType.OVAL_NO_FILL };
    private static final Color colors[] = { Color.RED, Color.GREEN, Color.YELLOW };

    public DrawingClient(int width, int height){
        this.width=width;
        this.height=height;
        Container contentPane = getContentPane();

        JButton startButton = new JButton("Draw");
        final JPanel panel = new JPanel();

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(startButton, BorderLayout.SOUTH);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        startButton.addActionListener(e -> {
                Graphics g = panel.getGraphics();
                for (int i = 0; i < 20; ++i) {
                    Shape shape = FlyweightShapeFactory.getShape(getRandomShape());
                    shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(),
                            getRandomHeight(), getRandomColor());
                }
            });
    }

    private FlyweightShapeFactory.ShapeType getRandomShape() {
        return shapes[(int) (Math.random() * shapes.length)];
    }

    private int getRandomX() {
        return (int) (Math.random() * width);
    }

    private int getRandomY() {
        return (int) (Math.random() * height);
    }

    private int getRandomWidth() {
        return (int) (Math.random() * (width / 10));
    }

    private int getRandomHeight() {
        return (int) (Math.random() * (height / 10));
    }

    private Color getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    public static void main(String[] args) {
        new DrawingClient(800,800);
    }
}
