package com.mine.userinterface; 	

import com.mine.board.Quarry;
import com.mine.miner.Miner;
import com.mine.pieces.*;

public class UserInterface {
	public void displayQuarry(Quarry quarry, Miner miner) {
		int size = quarry.getSize();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				/*if(miner.getX() == i && miner.getY() == j) displayMiner();
				else */
				displayPiece(quarry.getMine()[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	private void displayPiece(Piece piece) {
		if (piece instanceof Pit) System.out.print("P");
		else if (piece instanceof PotOfGold) System.out.print("G");
		else if (piece instanceof Beacon) System.out.print("B");
		else if (piece instanceof Stone) System.out.print("-");
		else System.out.print("X");
	}
	
	private void displayMiner() {
		System.out.print("M");
	}
}
