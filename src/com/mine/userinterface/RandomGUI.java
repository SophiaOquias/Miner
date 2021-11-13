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

        // get size of quarry
        Scanner sc = new Scanner(System.in);
        System.out.print("Input size of quarry: ");
        int size = Integer.parseInt(sc.nextLine());

        // initialize quarry, miner, and mining manager
        Quarry quarry = new Quarry(size);
        Miner miner = new Miner();
        MiningManager manager = new MiningManager(quarry, miner);

        char scanResult;

        while(!(manager.isGameOver(quarry, miner))) {

            scanResult = miner.scan(quarry);
            manager.incrementScan();

            // if scan does not detect a piece
            if(scanResult == 'N') {

                if(manager.isMinerFacingEdge(quarry, miner)) {

                    // rotate 180 degrees
                    for(int i = 0; i < 2; i++) {
                        miner.rotate();
                        manager.incrementRotate();
                    }

                }
                else {

                    miner.move();
                    manager.incrementMove();
                    miner.rotate();
                    manager.incrementRotate();

                }

            }

            // if scan detects a pit
            else if(scanResult == 'P') {

                miner.rotate();
                manager.incrementRotate();

            }

            // if scan detects a beacon
            else if(scanResult == 'B') {

                miner.move();
                manager.incrementMove();

                // while scan returns beacon
                while(miner.scan(quarry) == 'B') {
                    manager.incrementScan();
                    miner.move();
                    manager.incrementMove();
                }
                manager.incrementScan();

                // while scan does not return pot of gold
                while(miner.scan(quarry) != 'G') {
                    manager.incrementScan();
                    miner.rotate();
                    manager.incrementRotate();
                }
                manager.incrementScan();

                miner.move();
                manager.incrementMove();

            }

            // if scan detects a pot of gold
            else if(scanResult == 'G') {

                miner.move();
                manager.incrementMove();

            }

        }

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
                    int num = ((Beacon) currentPiece).getSquaresToGold();
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
