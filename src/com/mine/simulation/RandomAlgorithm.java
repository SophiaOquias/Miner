package com.mine.simulation;

import com.mine.pieces.*;
import com.mine.board.*; 

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

        Piece scanResult;

        while(!manager.isGameOver(quarry, miner)) {

            scanResult = miner.scan(quarry);

            if(scanResult == null) {
                manager.incrementScan();

                if(manager.isMinerFacingEdge()) {

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
            else if(scanResult instanceof Pit) {

                miner.rotate();
                manager.incrementRotate();

            }
            else if(scanResult instanceof Beacon) {

                miner.gotoBeacon(quarry);

                // while miner does not detect gold after scanning, rotate
                while(!(miner.scan(quarry) instanceof PotOfGold)) {
                    miner.rotate();
                    manager.incrementRotate();
                }

                miner.gotoGold(quarry);

            }
            else if(scanResult instanceof PotOfGold) {
                miner.gotoGold(quarry);
                
            }

        }

    }

}
