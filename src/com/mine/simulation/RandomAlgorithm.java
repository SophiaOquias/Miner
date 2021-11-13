package com.mine.simulation;

import com.mine.userinterface.RandomGUI;

import javax.swing.*;
import java.awt.*;

public class RandomAlgorithm extends JFrame {

    public RandomAlgorithm() {

        this.add(new RandomGUI());

        this.pack();

        this.setTitle("Random Rational Level Miner");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // makes window open on center
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            RandomAlgorithm random = new RandomAlgorithm();
            random.setVisible(true);
        });
    }
}
