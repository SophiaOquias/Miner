 package com.mine.board;
import java.util.ArrayList;
import java.util.Random;

import com.mine.miner.Miner;
import com.mine.pieces.Piece;
import com.mine.pieces.Position;
import com.mine.pieces.Stone;

 public class Quarry {
	
	private int size;
	private Piece[][] mine;
	private ArrayList<Position> PitPosList;
	private Position GoldPos;
	private ArrayList<Position> BeaconPosList;
	private ArrayList<Position> InvalidPoints;
	
	//Constructor
	public Quarry(int size) {
		this.size = size;
		this.mine = new Piece[size][size]; //mine property gets input sizes

		InvalidPoints.add(new Position (0, 0)); //Position of Miner
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				this.mine[i][j] = new Stone(new Position(i, j));
			}
		}
	}
	
	private boolean isOutofBounds(Position pos) { 
		if(pos.getX() >= 0 && pos.getX() < this.getSize() || pos.getY() >= 0 && pos.getY() < this.getSize()) {
			return false;
		}
		else return true;
	}
	
	private void getInvalidPoints() {
		if(!this.getGoldPos().equals(null) || !InvalidPoints.contains(this.getGoldPos())) {
			InvalidPoints.add(this.getGoldPos());
		}
		else if(!BeaconPosList.isEmpty()) {
			InvalidPoints.addAll(BeaconPosList);
		}
		else if (PitPosList.isEmpty()){ //Will only be called once.
			//This adds the points around the gold because Pits cant spawn a block near it.
			for(int i = 0; i < 8; i++) {
				Position invalidPos = new Position(0, 0); //It should never add (0, 0) to the position. This is for java to not bitch around with variable initialization.
				
				if(i == 0) invalidPos = new Position(GoldPos.getX(), GoldPos.getY() + 1); //North of Gold Pos
				else if(i == 1) invalidPos = new Position(GoldPos.getX(), GoldPos.getY() - 1); //South of Gold Pos
				else if(i == 2) invalidPos = new Position(GoldPos.getX() + 1, GoldPos.getY()); //East of Gold Pos
				else if(i == 3) invalidPos = new Position(GoldPos.getX() + 1, GoldPos.getY()); //West of Gold Pos
				
				else if(i == 4) invalidPos = new Position(GoldPos.getX() + 1, GoldPos.getY() + 1); //North East
				else if(i == 5) invalidPos = new Position(GoldPos.getX() - 1, GoldPos.getY() + 1); //North West
				else if(i == 6) invalidPos = new Position(GoldPos.getX() - 1, GoldPos.getY() - 1); //South West
				else if(i == 7) invalidPos = new Position(GoldPos.getX() + 1, GoldPos.getY() - 1); //South East
				
				if(!isOutofBounds(invalidPos) && !InvalidPoints.contains(invalidPos)) InvalidPoints.add(invalidPos);
			}
		}
		else {
			Position PitPosToAdd = PitPosList.get(this.getSize() - 1);
			
			InvalidPoints.add(PitPosToAdd); //Adds the last element in the Pit List
			
			for(int i = 0; i < 8; i++) {
				Position invalidPos = new Position(0, 0); //It should never add (0, 0) to the position. This is for java to not bitch around with variable initialization.
				
				if(i == 0) invalidPos = new Position(PitPosToAdd.getX(), PitPosToAdd.getY() + 1); //North of Gold Pos
				else if(i == 1) invalidPos = new Position(PitPosToAdd.getX(), PitPosToAdd.getY() - 1); //South of Gold Pos
				else if(i == 2) invalidPos = new Position(PitPosToAdd.getX() + 1, PitPosToAdd.getY()); //East of Gold Pos
				else if(i == 3) invalidPos = new Position(PitPosToAdd.getX() + 1, PitPosToAdd.getY()); //West of Gold Pos
				
				else if(i == 4) invalidPos = new Position(PitPosToAdd.getX() + 1, PitPosToAdd.getY() + 1); //North East
				else if(i == 5) invalidPos = new Position(PitPosToAdd.getX() - 1, PitPosToAdd.getY() + 1); //North West
				else if(i == 6) invalidPos = new Position(PitPosToAdd.getX() - 1, PitPosToAdd.getY() - 1); //South West
				else if(i == 7) invalidPos = new Position(PitPosToAdd.getX() + 1, PitPosToAdd.getY() - 1); //South East
				
				if(!isOutofBounds(invalidPos) && !InvalidPoints.contains(invalidPos)) InvalidPoints.add(invalidPos);
			}
		}
	}
	
	private Position spawnOnePit() {
		Random rand = new Random();
		int n = this.getSize();
		Position PitPos = new Position(0, 0);
		
		getInvalidPoints(); //When calling this, Invalid Points must have: pos of miner, pos of gold, poslist of all beacons, blocks ad
		
		do {
			PitPos.setX(rand.nextInt(n));
			PitPos.setY(rand.nextInt(n));
		}while(InvalidPoints.contains(PitPos));
		
		return PitPos;
	}
	//Spawn Functions ***CHECK FORMULA FOR HOW MANY TO SPAWN***
    public void spawnAllPits(int quarrySize){ //Random spawning. No 2 blocks near pot of gold. and not beside each other
    	
    }
    
    public void spawnBeacon(){ //Random spawning.
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
    
    private boolean spawnPotOfGoldVertically() { //Random spawning. No Pits 2 blocks near it.
    	Random rand = new Random();
    	
    	int val = rand.nextInt(1);  
    	
    	if (val == 1) return true;
    	else return false;
    }
    
    public void spawnPotOfGold(Quarry quarry){ //Random spawning. No Pits 2 blocks near it.
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
	
	public Position getGoldPos() {
		return this.GoldPos;
	}
	
	//Setters
	public void setSize(int input) {
		this.size = input;
	}
	
	public void setMine(Piece[][] mine) {
		this.mine = mine;
	}

	public void setGoldPos(Position goldPos) {
		this.GoldPos = goldPos;
	}
}
