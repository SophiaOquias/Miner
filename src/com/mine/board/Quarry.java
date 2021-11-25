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
		
		if(size >= 10)
			MAX_BEACONS  = (int) Math.floor(size * 0.1);
		else
			MAX_BEACONS = 1;
		
		MAX_PITS = (int) Math.floor(size * 0.25);
		
		InvalidPoints.add(new Position (0, 0)); //Position of Miner

		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				this.mine[i][j] = new Stone(new Position(i, j));

		spawnPotOfGold();
		mine[GoldPos.getX()][GoldPos.getY()] = new PotOfGold(GoldPos);

		spawnAllBeacons();
		for(int i = 0; i < BeaconPosList.size(); i++) {
			mine[BeaconPosList.get(i).getX()][BeaconPosList.get(i).getY()] = new Beacon(BeaconPosList.get(i));
		}

		spawnAllPits();
		for(int i = 0; i < PitPosList.size(); i++) {
			mine[PitPosList.get(i).getX()][PitPosList.get(i).getY()] = new Pit(PitPosList.get(i));
		}
	}
	
	//Methods
	private void addAdjacentPointsToInvalidPoints(Position pos) {
		InvalidPoints.add(new Position(pos.getX(), pos.getY() + 1));
		InvalidPoints.add(new Position(pos.getX(), pos.getY() - 1));
		InvalidPoints.add(new Position(pos.getX() + 1, pos.getY()));
		InvalidPoints.add(new Position(pos.getX() - 1, pos.getY()));
		
		InvalidPoints.add(new Position(pos.getX() + 1, pos.getY() + 1));
		InvalidPoints.add(new Position(pos.getX() - 1, pos.getY() + 1));
		InvalidPoints.add(new Position(pos.getX() + 1, pos.getY() - 1));
		InvalidPoints.add(new Position(pos.getX() - 1, pos.getY() - 1));
	}
	
	private boolean isInvalidPoint(Position pos) {
		boolean isInvalid = false;
		
		for(int i = 0; i < InvalidPoints.size(); i++)
			if (pos.equals(InvalidPoints.get(i))) isInvalid = true;
		
		return isInvalid;
	}
	
	private Position spawnOnePit() {
		Random rand = new Random();
		Position PitPos;
		
		do {
			PitPos = new Position(rand.nextInt(size), rand.nextInt(size));
		}while(isInvalidPoint(PitPos));
		
		InvalidPoints.add(PitPos);
		addAdjacentPointsToInvalidPoints(PitPos);
		
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

    	do {
    		BeaconPos = new Position(rand.nextInt(size), rand.nextInt(size));
    	}while(isInvalidPoint(BeaconPos));
    	
    	InvalidPoints.add(BeaconPos);
    	
    	return BeaconPos;
    }
    
    public void spawnAllBeacons(){ //Random spawning.
    	for(int i = 0; i < MAX_BEACONS; i++)
    		BeaconPosList.add(spawnOneBeacon());
    }
    
    public void spawnPotOfGold(){ //Random spawning. No Pits 2 blocks near it.
    	Random rand = new Random();
    	
    	do {
    		GoldPos = new Position(rand.nextInt(size), rand.nextInt(size));
    	}while(isInvalidPoint(GoldPos));
    	
    	InvalidPoints.add(GoldPos);
    	addAdjacentPointsToInvalidPoints(GoldPos);
    }
    
    //Getters and Setters
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
