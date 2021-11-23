package com.mine.pieces;

import com.mine.board.*;

public class Beacon extends Piece {
    // Constructor

    public Beacon(Position position) {
        this.pos = position;
    }

    // Methods

    private int calculateSquaresToGold(PotOfGold gold, Quarry quarry) {

        boolean isPitFound = false;
        int goldX = gold.pos.getX();
        int goldY = gold.pos.getY();
        int beaconX = this.pos.getX();
        int beaconY = this.pos.getY();
        int squaresToGold;
        
        // CHECK FOR PITS
        // check if pot of gold is in the same row
        if(goldX == beaconX) {

            // check if gold is to the right of the beacon
            if(goldY > beaconY) {
                for(int i = beaconY + 1; i < goldY; i++) {
                    if(quarry.getPiece(beaconX, i) instanceof Pit)
                        isPitFound = true;
                }
            }
            else { // check if gold is to the right of the beacon
                for(int i = beaconY - 1; i > goldY; i--) {
                    if(quarry.getPiece(beaconX, i) instanceof Pit)
                        isPitFound = true;
                }
            }

        }
        // check if pot of gold is in the same column
        else if(goldY == beaconY) {

            // check if gold is below the beacon
            if(goldX > beaconX) {
                for(int i = beaconX + 1; i < goldX; i++) {
                    if(quarry.getPiece(i, beaconY) instanceof Pit)
                        isPitFound = true;
                }
            }
            else { // check if gold is above the beacon
                for(int i = beaconX - 1; i > goldX; i--) {
                    if(quarry.getPiece(i, beaconY) instanceof Pit)
                        isPitFound = true;
                }
            }
        }

        // CALCULATION
        squaresToGold = Math.abs(goldX - beaconX) + Math.abs(goldY - beaconY);

        if(isPitFound)
            squaresToGold += 3;
        
        return squaresToGold;
    }
    
    public int getSquaresToGold(PotOfGold gold, Quarry quarry) {
    	
    	return calculateSquaresToGold(gold, quarry);
    }

}
