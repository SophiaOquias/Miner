 package com.mine.board;
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
	
	//Methods
	public void setSize(int input) {
		this.size = input;
	}
	
	public int getSize() {
		return size;
	}
	
	public Piece[][] getMine() {
		return mine;
	}

	public void setMine(Piece[][] mine) {
		this.mine = mine;
	}

	public Position getPitPos() {
		return PitPos;
	}

	public void setPitPos(Position pitPos) {
		PitPos = pitPos;
	}

	public Position getGoldPos() {
		return GoldPos;
	}

	public void setGoldPos(Position goldPos) {
		GoldPos = goldPos;
	}

	public Position getBeaconPos() {
		return BeaconPos;
	}

	public void setBeaconPos(Position beaconPos) {
		BeaconPos = beaconPos;
	}
}
