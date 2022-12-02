package main.gui;

import main.CSVEntry;
import main.ProgramArgs;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ClassifierGui {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JLabel textField;
    private JButton clearButton;
    private PaintPanel paintPanel;

    private final CSVEntry[] csvEntries;
    private final ProgramArgs programArgs;

    public ClassifierGui(CSVEntry[] csvEntries, ProgramArgs programArgs) {
        this.csvEntries = csvEntries;
        this.programArgs = programArgs;
        this.initGui();
    }

    private void initGui() {
        this.frame = new JFrame("Mnist Nearest Neighbour Classifier");
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.paintPanel = new PaintPanel();
        this.mainPanel.add(this.paintPanel, BorderLayout.CENTER);
        this.bottomPanel = new JPanel(new BorderLayout());
        this.textField = new JLabel("/");
        this.textField.setFont(new Font("Serif", Font.PLAIN, 50));
        this.mainPanel.add(this.textField, BorderLayout.EAST);
        this.clearButton = new JButton("Clear Canvas");
        this.clearButton.setFont(new Font("Serif", Font.PLAIN, 20));
        this.bottomPanel.add(this.clearButton, BorderLayout.CENTER);
        this.mainPanel.add(this.bottomPanel, BorderLayout.SOUTH);
        this.frame.add(this.mainPanel);
        this.frame.setSize(400, 440);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);

        this.setupClassificationTimer();
        this.initListeners();
    }

    private void setupClassificationTimer() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                classify();
            }
        }, 50L, 50L);
    }

    private void classify() {
        double[] testData = paintPanel.getPixels();
        int classification = programArgs.getClassifier().classify(csvEntries, testData);
        updateClassificationLabel(classification);
    }

    private void initListeners() {
        this.clearButton.addActionListener(e -> ClassifierGui.this.paintPanel.clearImage());
    }

    public void updateClassificationLabel(int newClassification) {
        this.textField.setText(String.valueOf(newClassification) + " ");
    }

}
