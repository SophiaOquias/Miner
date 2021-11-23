package com.mine.userinterface;

import com.mine.simulation.SmartMiner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartGUI {

    private JFrame smartFrame;
    private JFrame sliderFrame;
    private JSlider slider;
    private JButton okayButton;
    private JLabel sizeLabel;
    private JLabel statusbar;
    private int size;

    public void createSliderFrame() {

        // instantiating Swing components
        this.sliderFrame = new JFrame();
        this.okayButton = new JButton("Okay");
        this.slider = new JSlider(8, 64);
        this.sizeLabel = new JLabel("Selected Size: ");

        // sliderFrame settings
        this.sliderFrame.setSize(450, 200);
        this.sliderFrame.add(this.slider);
        this.sliderFrame.add(this.okayButton);
        this.sliderFrame.add(this.sizeLabel);
        this.sliderFrame.setLayout(null);
        this.sliderFrame.setLocationRelativeTo(null); // makes frame start at center
        this.sliderFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // "selected size: " text settings
        this.sizeLabel.setBounds(20, 70, 400, 100);

        // button settings
        this.okayButton.setBounds(250, 100, 100, 30);
        this.okayButton.addActionListener(new ButtonListener());

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

    public void createSmartFrame() {
        // initializing swing components
        this.smartFrame = new JFrame();
        this.statusbar = new JLabel("");
        JScrollPane scrollPane = new JScrollPane(new SmartMiner(this.size, this.statusbar));

        // Scroll pane (scroll bar) settings
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scroll speed settings
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // JFrame settings
        // sets frame size
        if (this.size <= 20) {
            this.smartFrame.setSize(new Dimension(
                    (size + 2) * 30,
                    (size + 2) * 30 + 50
            ));
        } else {
            this.smartFrame.setSize(600, 650);
        }
        this.smartFrame.add(scrollPane);
        this.smartFrame.add(this.statusbar, BorderLayout.SOUTH);
        this.smartFrame.setTitle("Smart Rational Level Miner");
        this.smartFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.smartFrame.setLocationRelativeTo(null); // makes window open on center
        this.smartFrame.setVisible(true);
    }

    public class ButtonListener implements ActionListener {

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