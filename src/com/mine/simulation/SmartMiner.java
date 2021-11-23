package com.mine.simulation;

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

public class SmartMiner extends JPanel {
	
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
	private JLabel statusbar;
	
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
	
	public SmartMiner(int size, JLabel statusbar) {
		this.initBoard(size);
		this.loadImages();

		this.statusbar = statusbar;

		// sets size of panel
		this.setPreferredSize(
				new Dimension(
						size * IMG_SIZE,
						size * IMG_SIZE + OFFSET
				)
		);

		// initializes statusbar
		this.statusbar.setText("Moves: \t Rotates: \t Scans: ");

		// initializes timer (responsible for animation of miner)
		this.timer = new Timer(DELAY, new FastPlay());

		// initializes buttons
		this.playButton = new JButton("Play");
		this.stepButton = new JButton("Play One Step");

		// adds ActionListeners to buttons
		this.playButton.addActionListener(new FastPlay());
		this.stepButton.addActionListener(new OneStep());

		// adds buttons to panel
		this.add(this.playButton);
		this.add(this.stepButton);
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
		this.miner = nodeList.peek();
		this.exploredNodes.add(nodeList.pop());
		this.expand();
	}

	private void expand() {
		// instantiating new miner nodes
		Miner moveMiner = new Miner();
		Miner rotateMiner = new Miner();

		// copy information of current state of miner
		copy(moveMiner, miner);
		copy(rotateMiner, miner);

		// performs move only if miner is not facing edge 
		if(!manager.isMinerFacingEdge(quarry, miner)) {
			moveMiner.move();
			moveMiner.incrementMoves();
			moveMiner.setHeuristicValue(moveMiner.scan(quarry));
			moveMiner.incrementScans();
		}

		rotateMiner.rotate();
		rotateMiner.incrementRotates();
		rotateMiner.setHeuristicValue(rotateMiner.scan(quarry));
		moveMiner.incrementScans();

		// pushes only rotateMiner if miner is facing edge (OOB) or node has already been explored
		if(manager.isMinerFacingEdge(quarry, miner) ||
				isInList(exploredNodes, moveMiner) && !isInList(exploredNodes, rotateMiner)) {
			nodeList.push(rotateMiner);
		}
		// pushes only moveMiner if node has already been explored
		else if(isInList(exploredNodes, rotateMiner) && !isInList(exploredNodes, rotateMiner)) {
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
		m1.setMoves(m2.getMoves());
		m1.setRotates(m2.getRotates());
		m1.setScans(m2.getScans());
	}

	private void updateStatusbar() {
		statusbar.setText("Moves: " + miner.getMoves() +
				"    Rotates: " +  miner.getRotates() +
				"    Scans: " + miner.getScans());
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
				miner.getX() * IMG_SIZE + OFFSET,
				this);
	}

	private class FastPlay implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!nodeList.isEmpty() && !manager.isOnPotOfGold(quarry, miner)) {
				timer.start();
				bestFirstSearch();
				repaint();
				updateStatusbar();
			}
			else {
				timer.stop();
			}
		}
	}

	private class OneStep implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!nodeList.isEmpty() && !manager.isOnPotOfGold(quarry, miner)) {
				bestFirstSearch();
				repaint();
				updateStatusbar();
			}
		}
	}

}
