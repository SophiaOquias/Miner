 package com.mine.board;
import com.mine.pieces.Piece;

public class Quarry {
	
	private int size;

	private Piece[][] mine;
	
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
}
