package com.mine.userinterface;

import com.mine.simulation.SmartMiner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {

    private JFrame sliderFrame;
    private JSlider slider;
    private JLabel sizeLabel;
    private int size;

    public void createSliderFrame() {

        // instantiating Swing components
        this.sliderFrame = new JFrame();
        JButton randomButton = new JButton("Random Miner");
        JButton smartButton = new JButton("Smart Miner");
        this.slider = new JSlider(8, 64);
        this.sizeLabel = new JLabel("Selected Size: ");

        // sliderFrame settings
        this.sliderFrame.setSize(450, 200);
        this.sliderFrame.add(this.slider);
        this.sliderFrame.add(smartButton);
        this.sliderFrame.add(randomButton);
        this.sliderFrame.add(this.sizeLabel);
        this.sliderFrame.setLayout(null);
        this.sliderFrame.setLocationRelativeTo(null); // makes frame start at center
        this.sliderFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // "selected size: " text settings
        this.sizeLabel.setBounds(20, 70, 400, 100);

        // button settings
        // randomButton settings
        randomButton.setBounds(160, 105, 125, 30);
        // still need to add actionListener to randomButton

        // smartButton settings
        smartButton.setBounds(290, 105, 125, 30);
        smartButton.addActionListener(new SmartButtonListener());

        // slider settings
        this.slider.setBounds(10, 0, 400, 100);
        this.slider.setMinorTickSpacing(1);
        this.slider.setMajorTickSpacing(8);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        this.slider.addChangeListener(new SliderListener());
        this.slider.setVisible(true);

        this.sliderFrame.setVisible(true);

    }

    public void createRandomFrame() {
        // initializing swing components
        JFrame randomFrame = new JFrame();
        JLabel statusbar = new JLabel("");
        JScrollPane scrollPane = new JScrollPane(); // need to add RandomMiner(this.size, statusbar);

        initializeScrollPane(scrollPane);

        // JFrame settings
        // sets frame size
        if (this.size <= 20) {
            randomFrame.setSize(new Dimension(
                    (size + 2) * 30,
                    (size + 2) * 30 + 50
            ));
        } else {
            randomFrame.setSize(600, 650);
        }
        randomFrame.add(scrollPane);
        randomFrame.add(statusbar, BorderLayout.SOUTH);
        randomFrame.setTitle("Smart Rational Level Miner");
        randomFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        randomFrame.setLocationRelativeTo(null); // makes window open on center
        randomFrame.setVisible(true);

    }

    public void createSmartFrame() {
        // initializing swing components
        JFrame smartFrame = new JFrame();
        JLabel statusbar = new JLabel("");
        JScrollPane scrollPane = new JScrollPane(new SmartMiner(this.size, statusbar));

        initializeScrollPane(scrollPane);

        // JFrame settings
        // sets frame size
        if (this.size <= 20) {
            smartFrame.setSize(new Dimension(
                    (size + 2) * 30,
                    (size + 2) * 30 + 50
            ));
        } else {
            smartFrame.setSize(600, 650);
        }
        smartFrame.add(scrollPane);
        smartFrame.add(statusbar, BorderLayout.SOUTH);
        smartFrame.setTitle("Smart Rational Level Miner");
        smartFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        smartFrame.setLocationRelativeTo(null); // makes window open on center
        smartFrame.setVisible(true);
    }

    public void initializeScrollPane(JScrollPane scrollPane) {
        // Scroll pane (scroll bar) settings
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scroll speed settings
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    public class SmartButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sliderFrame.setVisible(false);
            createSmartFrame();
        }
    }

    public class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            size = slider.getValue();
            sizeLabel.setText("Selected Size: " + size);
        }
    }
}