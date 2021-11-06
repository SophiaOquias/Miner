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
        spawnPit(quarry);
        spawnPotOfGold(quarry);
        spawnBeacon(quarry);
        spawnMiner(miner);
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

    //Spawn Functions
    public void spawnPit(Quarry quarry){
    	int x = 0, y = 0;
    	
    	Random rand = new Random();
    	
    	while(x == 0) 
    		x = rand.nextInt(quarry.getSize());
    	
    	while(y == 0) 
    		y = rand.nextInt(quarry.getSize());
    	
    	quarry.setPitPos(new Position(x, y));
    }

    public void spawnPotOfGold(Quarry quarry){
    	int x, y;
    	
    	//THIS IS TEMPORARY
    	x = y =  2;
    	
    	quarry.setGoldPos(new Position(x, y));
    }

    public void spawnBeacon(Quarry quarry){
    	int x, y;
    	
    	//THIS IS TEMPORARY
    	x = y =  3;
    	
    	quarry.setBeaconPos(new Position(x, y));
    }
    
    public void spawnMiner(Miner miner){
    	miner.setPosition(new Position(0, 0));
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
