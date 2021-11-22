package com.mine.simulation;

import javax.swing.*;
import com.mine.userinterface.SmartGUI;

import java.awt.*;

public class SmartAlgorithm extends JFrame {

    public SmartAlgorithm() {
        int size = 8;
        this.add(new SmartGUI(size));

        this.pack();

        this.setTitle("Smart Rational Level Miner");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // makes window open on center
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SmartAlgorithm smart = new SmartAlgorithm();
            smart.setVisible(true);
        });
    }
}
