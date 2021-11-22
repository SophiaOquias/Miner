package com.mine.userinterface;

import com.mine.pieces.*;
import com.mine.board.*;
import com.mine.miner.Miner;
import com.mine.miner.MiningManager;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SmartGUI extends JPanel {
	
	//Properties
	private Quarry quarry;
	private Miner miner;
	private MiningManager manager;
	private Stack<Miner> nodeList;
	private ArrayList<Miner> exploredNodes;

	//GUI Components
	private Image[] images;
	private Timer timer;
	private JButton playButton;
	private JButton stepButton;
	
	//Constants
	private final int IMG_SIZE = 30;
	private final int OFFSET = 50;
	private final int DELAY = 140;
	
	//Image constants
	private final int NUM_IMG = 8;
	private final int STONE = 0;
	// 1 - 4 is reserved for MINER
	private final int BEACON = 5;
	private final int PIT = 6;
	private final int GOLD = 7;
	
	public SmartGUI(int size) {
		this.initBoard(size);
		this.loadImages();

		this.setPreferredSize(
				new Dimension(
						quarry.getSize() * IMG_SIZE,
						quarry.getSize() * IMG_SIZE + OFFSET
				)
		);

		this.timer = new Timer(DELAY, new FastPlay());

		this.playButton = new JButton("Play");
		this.stepButton = new JButton("Play One Step");

		this.playButton.addActionListener(new FastPlay());

		this.add(this.playButton);
	}

	private void initBoard(int size) {
		this.quarry = new Quarry(size);
		this.miner = new Miner();
		this.manager = new MiningManager(quarry, miner);

		this.nodeList = new Stack<>();
		this.nodeList.push(this.miner);
		this.exploredNodes = new ArrayList<>();
	}

	private void loadImages() {
		images = new Image[NUM_IMG];

		for(int i = 0; i < NUM_IMG; i++) {
			var path = "src/images/" + i + ".png";
			this.images[i] = (new ImageIcon(path)).getImage().getScaledInstance(IMG_SIZE, IMG_SIZE, 1);
		}
	}
	
	private boolean minerCompare(Miner m1, Miner m2) {
	    if(m1.getX() == m2.getX() &&
	        m1.getY() == m2.getY() &&
	        m1.getFront() == m2.getFront())
	        return true;
	   return false;
	}

	private boolean isInList(ArrayList<Miner> nodeList, Miner node) {
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

	@Override
	public void paintComponent(Graphics g) {

		for(int i = 0; i < quarry.getSize(); i++) {
			for(int j = 0; j < quarry.getSize(); j++) {

				int x = j * IMG_SIZE;
				int y = i * IMG_SIZE + OFFSET;
				Piece currentPiece = this.quarry.getPiece(i, j);

				// draw all other pieces
				if(currentPiece instanceof Stone)
					g.drawImage(this.images[STONE], x, y, this);
				else if(currentPiece instanceof Pit)
					g.drawImage(this.images[PIT], x, y, this);
				else if(currentPiece instanceof Beacon) {
					g.drawImage(this.images[BEACON], x, y, this);
					int num = ((Beacon) currentPiece).getSquaresToGold();
					g.drawString("" + num, x + 10, y + 25);
				}
				else if(currentPiece instanceof PotOfGold)
					g.drawImage(this.images[GOLD], x, y, this);
			}
		}

		// draw miner
		g.drawImage(this.images[miner.getFront()],
				miner.getY() * IMG_SIZE,
				OFFSET + miner.getX() * IMG_SIZE,
				this);
	}

	private class FastPlay implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bestFirstSearch();
		}
	}

}
