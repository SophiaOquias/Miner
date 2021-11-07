package com.mine.simulation;

import com.mine.pieces.*;
import com.mine.board.*;
import com.mine.miner.Miner;
import com.mine.miner.MiningManager;

import java.util.*;

public class RandomAlgorithm {

    public static void main(String[] args) {

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

}
