 package com.mine.board;
import java.util.ArrayList;
import java.util.Random;

import com.mine.miner.Miner;
import com.mine.pieces.Beacon;
import com.mine.pieces.Piece;
import com.mine.pieces.Pit;
import com.mine.pieces.Position;
import com.mine.pieces.PotOfGold;
import com.mine.pieces.Stone;
import com.mine.userinterface.UserInterface;

 public class Quarry {
	
	private int size;
	private Piece[][] mine;
	private ArrayList<Position> PitPosList = new ArrayList<Position>();
	private Position GoldPos;
	private ArrayList<Position> BeaconPosList = new ArrayList<Position>();
	private ArrayList<Position> InvalidPoints = new ArrayList<Position>();
	
	private final int MAX_BEACONS;
	private final int MAX_PITS;
	
	//Constructor
	public Quarry(int size) {
		this.size = size;
		this.mine = new Piece[size][size]; //mine property gets input sizes
		
		if(size * 0.1 >= 10) MAX_BEACONS  = (int) Math.floor(size * 0.1);
		else MAX_BEACONS = 1;
		
		MAX_PITS = (int) Math.floor(size * 0.25);
		
		InvalidPoints.add(new Position (0, 0)); //Position of Miner
		
		System.out.println("Filling quarry with Stone...");
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				this.mine[i][j] = new Stone(new Position(i, j));
		
		System.out.println("Spawning Gold...");
		spawnPotOfGold();
		System.out.println("Importing Gold...");
		mine[GoldPos.getX()][GoldPos.getY()] = new PotOfGold(GoldPos);
		
		System.out.println("Spawning Beacons...");
		spawnAllBeacons();
		System.out.println("Importing Beacons... n = " + BeaconPosList.size());
		for(int i = 0; i < BeaconPosList.size(); i++) {
			mine[BeaconPosList.get(i).getX()][BeaconPosList.get(i).getY()] = new Beacon(BeaconPosList.get(i));
			System.out.printf("x = %d, y = %d\n", BeaconPosList.get(i).getX(), BeaconPosList.get(i).getY());
		}
		
		System.out.println("Spawning Pits...");
		spawnAllPits();
		System.out.println("Importing Pits... n = " + PitPosList.size());
		for(int i = 0; i < PitPosList.size(); i++) {
			mine[PitPosList.get(i).getX()][PitPosList.get(i).getY()] = new Pit(PitPosList.get(i));
			System.out.printf("x = %d, y = %d\n", PitPosList.get(i).getX(), PitPosList.get(i).getY());
		}		
	}
	
	private boolean isOutofBounds(Position pos) { 
		if(pos.getX() >= 0 && pos.getX() < this.getSize() || pos.getY() >= 0 && pos.getY() < this.getSize()) {
			return false;
		}
		else return true;
	}
	
	private void getInvalidPoints() {
		if(!this.getGoldPos().equals(null) && !InvalidPoints.contains(this.getGoldPos())) {
			InvalidPoints.add(this.getGoldPos());
		}
		else if(!BeaconPosList.isEmpty() && BeaconPosList.size() < MAX_BEACONS) {
			InvalidPoints.add(BeaconPosList.get(BeaconPosList.size() - 1));
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
		else if (!PitPosList.isEmpty()){
			Position PitPosToAdd = PitPosList.get(PitPosList.size() - 1);
			
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
		Position PitPos;
		
		getInvalidPoints(); //When calling this, Invalid Points must have: pos of miner, pos of gold, poslist of all beacons, blocks ad
		
		do {
			PitPos = new Position(rand.nextInt(size - 1), rand.nextInt(size - 1));
		}while(InvalidPoints.contains(PitPos));
		
		return PitPos;
	}
	
	//Spawn Functions ***CHECK FORMULA FOR HOW MANY TO SPAWN***
    public void spawnAllPits(){ //Random spawning. No 2 blocks near pot of gold. and not beside each other
    	for(int i = 0; i < MAX_PITS; i++)
    		PitPosList.add(spawnOnePit());
    }
    
    private Position spawnOneBeacon() {
    	Random rand = new Random();
    	Position BeaconPos;
    	
    	getInvalidPoints();
    	
    	do {
    		BeaconPos = new Position(rand.nextInt(size - 1), rand.nextInt(size - 1));
    	}while(InvalidPoints.contains(BeaconPos));
    	
    	return BeaconPos;
    }
    
    public void spawnAllBeacons(){ //Random spawning.
    	for(int i = 0; i < MAX_BEACONS; i++)
    		BeaconPosList.add(spawnOneBeacon());
    }
    
    public void spawnPotOfGold(){ //Random spawning. No Pits 2 blocks near it.
    	Random rand = new Random();
    	
    	do {
    		GoldPos = new Position(rand.nextInt(size - 1), rand.nextInt(size - 1));
    	}while(InvalidPoints.contains(GoldPos));
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
	
	public static void main(String[] args) {
		Quarry quarry = new Quarry(7);
		UserInterface ui = new UserInterface();
		Miner miner = new Miner();
		
		ui.displayQuarry(quarry, miner);
	}
}
