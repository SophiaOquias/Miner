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

        while(!(manager.isGameOver(quarry, miner))) {

            scanResult = miner.scan(quarry);
            manager.incrementScan();

            if(scanResult == null) {

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

                miner.move();
                manager.incrementMove();

                // while scan returns beacon
                while(miner.scan(quarry) instanceof Beacon) {
                    manager.incrementScan();
                    miner.move();
                    manager.incrementMove();
                }
                miner.incrementScan();

                // while scan does not return pot of gold
                while(!(miner.scan(quarry) instanceof PotOfGold)) {
                    manager.incrementScan();
                    miner.rotate();
                    manager.incrementRotate();
                }
                miner.incrementScan();

                miner.move();
                manager.incrementMove();

            }
            else if(scanResult instanceof PotOfGold) {

                miner.move();
                manager.incrementMove();

            }

        }

    }

}
