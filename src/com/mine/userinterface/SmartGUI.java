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
	private Stack<Miner> nodeList;
	private ArrayList<Miner> exploredNodes;
	
	//Constants
	private final int IMG_SIZE = 30;
	private final int OFFSET = 50;
	
	//Image constants
	private final int NUM_IMG = 8;
	private final int STONE = 0;
	// 1 - 4 is reserved for MINER
	private final int BEACON = 5;
	private final int PIT = 6;
	private final int GOLD = 7;
	
	public SmartGUI(int size) {
		this.quarry = new Quarry(size);
		this.miner = new Miner();
		this.manager = new MiningManager(quarry, miner);
		this.nodeList = new Stack<>();
		this.nodeList.push(this.miner);
		this.exploredNodes = new ArrayList<>();
	}
	
	public boolean minerCompare(Miner m1, Miner m2) {
	    if(m1.getX() == m2.getX() &&
	        m1.getY() == m2.getY() &&
	        m1.getFront() == m2.getFront())
	        return true;
	   return false;
	}

	public boolean isInList(ArrayList<Miner> nodeList, Miner node) {
		for(int i = 0; i < nodeList.size(); i++) {
		    if(minerCompare(nodeList.get(i), node))
		        return true; 
		}
		
		return false; 
    }
	
	public void bestFirstSearch() {

		// will have to change while to if statement if using javax.swing.Timer
		while(!this.nodeList.isEmpty() && !this.manager.isOnPotOfGold(this.quarry, this.miner)) {
			this.miner = nodeList.peek();
			this.exploredNodes.add(nodeList.pop());
			this.expand();
		}
	}

	private void expand() {
		// instantiating new miner nodes
		Miner moveMiner = new Miner();
		Miner rotateMiner = new Miner();

		// copy information of current state of miner
		copy(moveMiner, miner);
		copy(rotateMiner, miner);

		if(!manager.isMinerFacingEdge(quarry, miner)) {
			moveMiner.move();
			moveMiner.setHeuristicValue(moveMiner.scan(quarry));
		}

		rotateMiner.rotate();
		rotateMiner.setHeuristicValue(rotateMiner.scan(quarry));

		// pushes only rotateMiner if miner is facing edge (OOB) or node has already been explored
		if(manager.isMinerFacingEdge(quarry, miner) || isInList(exploredNodes, moveMiner)) {
			nodeList.push(rotateMiner);
		}
		// pushes only moveMiner if node has already been explored
		else if(isInList(exploredNodes, rotateMiner)) {
			nodeList.push(moveMiner);
		}
		// check if moveNode's h(n) >= rotateNode's h(n)
		// whichever is pushed last becomes top of stack
		else if(moveMiner.getHeuristicValue() >= rotateMiner.getHeuristicValue()) {
			nodeList.push(rotateMiner); // push rotate node
			nodeList.push(moveMiner); // push move node
		}
		else {
			nodeList.push(moveMiner); // push move node
			nodeList.push(rotateMiner); // push rotate node
		}
	}

	// copies position and orientation of m2 to m1
	private void copy(Miner m1, Miner m2) {
		m1.setX(m2.getX());
		m1.setY(m2.getY());
		m1.setFront(m2.getFront());
	}

}
