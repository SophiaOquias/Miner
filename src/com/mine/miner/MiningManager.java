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
    	return true;
    }

    public boolean isOnPotOfGold(Quarry quarry, Miner miner){
        return true;
    }

    public boolean isGameOver(Quarry quarry, Miner miner){
        return true;
    }

    public boolean isMinerFacingEdge(Quarry quarry, Miner miner){
        return true;
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
