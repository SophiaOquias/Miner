package com.mine.miner;

import com.mine.pieces.*;
import com.mine.board.*;

import java.util.Random;

public class MiningManager {
    
    // Properties

    private int moveCounter;
    private int rotateCounter;
    private int scanCounter;

    // Constructor

    public MiningManager(Quarry quarry, Miner miner){
        
    }

    // Methods
    
    //Game Functions
    public boolean isOnPit(Quarry quarry, Miner miner){
    	if (quarry.getPitPos().getX() == miner.getX() && quarry.getPitPos().getY() == miner.getY()) //miner's X & Y position == pit position in quarry
    		return true;
    	return false;
    }

    public boolean isOnPotOfGold(Quarry quarry, Miner miner){
    	if(quarry.getPiece(miner.getX(), miner.getY()) instanceof PotOfGold)
    	    return true; 
        
        return false;
    }

    public boolean isGameOver(Quarry quarry, Miner miner){
        if (isOnPit(quarry,miner) == true || isOnPotOfGold(quarry, miner) == true) 
        	return true;
    	return false;
    }

    public boolean isMinerFacingEdge(Quarry quarry, Miner miner){

        if(miner.getFront() == miner.NORTH && miner.getX() == 0)
            return true;
        else if(miner.getFront() == miner.EAST && miner.getY() == quarry.getSize() - 1)
            return true;
        else if(miner.getFront() == miner.SOUTH && miner.getX() == quarry.getSize() - 1)
            return true;
        else if(miner.getFront() == miner.WEST && miner.getY() == 0)
            return true;

        return false;
    }
    
    //Miner Functions
    public void incrementMove(){
        this.moveCounter = moveCounter++;
    }
 
    public void incrementRotate(){
        this.rotateCounter = rotateCounter++;
    }

    public void incrementScan(){
        this.scanCounter = scanCounter++;
    }
    
    public void resetCounters(){
        this.moveCounter = 0;
        this.rotateCounter = 0;
        this.scanCounter = 0;
    }


}
