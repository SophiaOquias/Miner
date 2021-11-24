package com.mine.userinterface;

import com.mine.pieces.*;
import com.mine.board.*;
import com.mine.miner.Miner;
import com.mine.miner.MiningManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RandomGUI extends JPanel{

    // Properties
    private Quarry quarry;
    private Miner miner;
    private MiningManager manager;
    private Image[] images;

    // Defining Constants
    private final int IMG_SIZE = 30;
    private final int OFFSET = 50; // length of offset quarry board and space for buttons
    // constants for images
    private final int NUM_IMG = 5;
    private final int MINER = 1;
    private final int BEACON = 2;
    private final int PIT = 3;
    private final int GOLD = 4;
    private final int STONE = 5;

    public RandomGUI() {
        // need to make quarry size input, possibly a slider

        this.loadImages();
        this.initBoard();

        this.setPreferredSize(
                new Dimension(
                        quarry.getSize() * IMG_SIZE,
                        quarry.getSize() * IMG_SIZE + OFFSET
                )
        );

    }

    private void loadImages() {

        images = new Image[NUM_IMG];

        for(int i = 1; i <= NUM_IMG; i++) {
            String path = "src/images/" + i + ".png";
            this.images[i] = (new ImageIcon(path)).getImage();
        }
    }

    private void initBoard() {
        // spawn quarry, pieces, and miner
    }

    public void randomAlgorithm() {


    }

    @Override
    public void paintComponent(Graphics g) {

        // draw quarry
        for(int i = 0; i < quarry.getSize(); i++) {
            for(int j = 0; j < quarry.getSize(); j++) {

                int x = j * IMG_SIZE;
                int y = i * IMG_SIZE + OFFSET;
                Piece currentPiece = this.quarry.getPiece(i, j);

                if(currentPiece instanceof Stone)
                    g.drawImage(this.images[STONE], x, y, this);
                else if(currentPiece instanceof Pit)
                    g.drawImage(this.images[PIT], x, y, this);
                else if(currentPiece instanceof Beacon) {
                    g.drawImage(this.images[BEACON], x, y, this);
                    int num = ((Beacon) currentPiece).
                            getSquaresToGold((PotOfGold) quarry.getPiece(
                                    quarry.getGoldPos().getX(),
                                    quarry.getGoldPos().getY()),
                                    quarry);
                    g.drawString("" + num, 2 * IMG_SIZE + 10 , 2 * IMG_SIZE + 45);
                }
                else if(currentPiece instanceof PotOfGold)
                    g.drawImage(this.images[GOLD], x, y, this);
            }
        }

        // draw miner
        g.drawImage(this.images[MINER], 0, OFFSET, this);

    }

}
