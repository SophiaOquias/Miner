package com.mine.userinterface;

import com.mine.pieces.*;
import com.mine.board.*;
import com.mine.miner.Miner;
import com.mine.miner.MiningManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SmartGUI extends JPanel {
	
	//Properties
	private Quarry quarry;
	private Miner miner;
	private MiningManager manager;
	private Image[] images;
	
	//Constants
	private final int IMG_SIZE = 30;
	private final int OFFSET = 50;
	
	//Image constants
	private final int NUM_IMG = 5;
	private final int MINER = 1;
	private final int BEACON = 2;
	private final int PIT = 3;
	private final int GOLD = 4;
	private final int STONE = 5;
	
	public SmartGUI(int size) {
		this.quarry = new Quarry(size);
		this.manager = new MiningManager(quarry, miner);
	}
	
	public boolean minerCompare(Miner m1, Miner m2) {
	    if(m1.getX() == m2.getX() &&
	        m1.getY() == m2.getY() &&
	        m1.getFront() == m2.getFront())
	        return true;
	   return false;
	}
	
	@SuppressWarnings("unused")
	public boolean isInList(ArrayList<Miner> nodeList, Miner node) {
		for(int i = 0; i < nodeList.size(); i++) {
		    if(minerCompare(nodeList.get(i), node));
		        return true; 
		}
		
		return false; 
    }
	
	public void smartAlgorithm() {
		Stack<Miner> nodeList = new Stack<>();
		ArrayList<Miner> exploredNodes = new ArrayList<>();
		

		exploredNodes.add(new Miner());

		while(!nodeList.isEmpty() && !manager.isOnPotOfGold(quarry, miner)) {
			exploredNodes.add(nodeList.pop()); 
			
			for(int i = 0; i < 2; i++) {
				Miner[] tempList = new Miner[2];
				
				// adding possible actions to tempList 
				if(i == 0) { // move action 
				    tempList[i] = new Miner();
				    if(!(manager.isMinerFacingEdge(quarry, miner))) {
				        tempList[i].move();
                        tempList[i].setHeuristicValue(tempList[i].scan(quarry));
				    }
				    else {
                        tempList[i] = null; 
				    }
				}
				else { //rotate action
				    tempList[i] = new Miner();
				    tempList[i].rotate();
				    tempList[i].setHeuristicValue(tempList[i].scan(quarry));
				}
				
				// adding nodes to stack based on greater h(n) value 
				if(isInList(exploredNodes, tempList[0]) || tempList[0] == null) {
				    nodeList.push(tempList[1]); 							
				}
				else if(isInList(exploredNodes, tempList[1])) {
				    nodeList.push(tempList[0]);
				}
				//check if moveNode's h(n) >= rotateNode's h(n)
				else if(tempList[0].getHeuristicValue() >= tempList[1].getHeuristicValue()) {
					nodeList.push(tempList[1]); // push rotate node
					nodeList.push(tempList[0]); // push move node
				}
				else {
					nodeList.push(tempList[0]); // push move node
					nodeList.push(tempList[1]); // push rotate node
				}
				
			}
		}
	}

}
