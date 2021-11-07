 package com.mine.board;
import java.util.Random;

import com.mine.miner.Miner;
import com.mine.pieces.Piece;
import com.mine.pieces.Position;

public class Quarry {
	
	private int size;
	private Piece[][] mine;
	private Position PitPos;
	private Position GoldPos;
	private Position BeaconPos;
	
	//Constructor
	public Quarry(int size) {
		this.size = size;
		this.mine = new Piece[size][size]; //mine property gets input sizes
	}
	
	//Spawn Functions
    public void spawnPit(){
    	int x, y;
    	
    	Random rand = new Random();
    	
    	do{
    		x = rand.nextInt(this.getSize());
    		y = rand.nextInt(this.getSize());
    	}while(x == 0 && y == 0);
    	
    	this.setPitPos(new Position(x, y));
    }
    
    public void spawnBeacon(Quarry quarry){
    	int x, y;
    	Random rand = new Random();
    	int invalidXPoint = this.getPitPos().getX();
    	int invalidYPoint = this.getPitPos().getY();
    	
    	do {
    		do{
        		x = rand.nextInt(this.getSize());
        	}while(x == invalidXPoint);
        	
        	do{
        		y = rand.nextInt(this.getSize());
        	}while(y == invalidYPoint); //Beacon cant spawn adjacent to 
    	}while(x == 0 && y == 0); //This ensures beacon does not spawn on miner
    	
    	
    	quarry.setBeaconPos(new Position(x, y));
    }
    
    private boolean spawnPotOfGoldVertically() {
    	Random rand = new Random();
    	
    	int val = rand.nextInt(1);  
    	
    	if (val == 1) return true;
    	else return false;
    }
    
    public void spawnPotOfGold(Quarry quarry){
    	int x, y;
    	Random rand = new Random();
    	int validXPoint = this.getBeaconPos().getX();
    	int validYPoint = this.getBeaconPos().getY();
    	
    	if(this.spawnPotOfGoldVertically()) {
    		y = validYPoint;
    		
    		do {
    			x = rand.nextInt(this.getSize());
    		}while(x == validXPoint);
    	}
    	else {
    		x = validXPoint;
    		
    		do {
    			y = rand.nextInt(this.getSize());
    		}while(y == validYPoint);
    	}
    	
    	quarry.setGoldPos(new Position(x, y));
    }

    public void spawnMiner(Miner miner){
    	miner.setX(0);
    	miner.setY(0);
    }
    
	//Methods
    
    //Getters
	public int getSize() {
		return this.size;
	}
	
	public Piece getPiece(int x, int y) {
		return this.mine[x][y];
	}
	
	public Piece[][] getMine() {
		return this.mine;
	}
	
	public Position getPitPos() {
		return this.PitPos;
	}
	
	public Position getGoldPos() {
		return this.GoldPos;
	}
	
	public Position getBeaconPos() {
		return this.BeaconPos;
	}
	
	
	//Setters
	public void setSize(int input) {
		this.size = input;
	}
	
	public void setMine(Piece[][] mine) {
		this.mine = mine;
	}

	public void setPitPos(Position pitPos) {
		this.PitPos = pitPos;
	}

	public void setGoldPos(Position goldPos) {
		this.GoldPos = goldPos;
	}

	public void setBeaconPos(Position beaconPos) {
		this.BeaconPos = beaconPos;
	}
}
