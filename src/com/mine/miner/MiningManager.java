package com.mine.miner;

import com.mine.pieces.*;
import com.mine.board.*;

import java.util.*;


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
        spawnMiner(quarry);
    }

    // Methods

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

    public void spawnPit(Quarry quarry){

    }

    public void spawnPotOfGold(Quarry quarry){

    }

    public void spawnBeacon(Quarry quarry){

    }
    
    public void spawnMiner(Quarry quarry){

    }

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
