package com.mine.miner;

import com.mine.pieces.*;
import com.mine.board.*;

import java.util.*;

public class Miner {
    
    // Properties

    private int front;
    private int x;
    private int y;
    private int heuristicValue;
    private int moves = 0;
    private int scans = 0;
    private int rotates = 0;

    // Define Constants
    public final int NORTH = 1;
    public final int EAST  = 2;
    public final int SOUTH = 3;
    public final int WEST  = 4;

    // Constructor
    public Miner(){
        this.front = EAST;
        this.x = 0;
        this.y = 0;
    }

    // Methods

    public void move(){

        if(this.front == NORTH) this.x--;
        else if(this.front == EAST) this.y++;
        else if(this.front == SOUTH) this.x++;
        else if(this.front == WEST) this.y--;

    }
    
    public char scan(Quarry quarry){

        if(this.front == NORTH) {
            for(int i = this.x - 1; i >= 0; i--) {
                Piece scannedPiece = quarry.getPiece(i, this.y);
                if(scannedPiece instanceof Beacon) return 'B';
                else if(scannedPiece instanceof PotOfGold) return 'G';
                else if(scannedPiece instanceof Pit) return 'P';
            }
        }

        else if(this.front == EAST) {
            for(int i = this.y + 1; i < quarry.getSize(); i++) {
                Piece scannedPiece = quarry.getPiece(this.x, i);
                if(scannedPiece instanceof Beacon) return 'B';
                else if(scannedPiece instanceof PotOfGold) return 'G';
                else if(scannedPiece instanceof Pit) return 'P';
            }
        }

        else if(this.front == SOUTH) {
            for(int i = this.x + 1; i < quarry.getSize(); i++) {
                Piece scannedPiece = quarry.getPiece(i, this.y);
                if(scannedPiece instanceof Beacon) return 'B';
                else if(scannedPiece instanceof PotOfGold) return 'G';
                else if(scannedPiece instanceof Pit) return 'P';
            }
        }

        if(this.front == WEST) {
            for(int i = this.y - 1; i >= 0; i--) {
                Piece scannedPiece = quarry.getPiece(this.x, i);
                if(scannedPiece instanceof Beacon) return 'B';
                else if(scannedPiece instanceof PotOfGold) return 'G';
                else if(scannedPiece instanceof Pit) return 'P';
            }
        }

        return 'N'; // not sure about this, can't return null for some reason
    }

    public void rotate(){

        if(this.front == WEST)
            this.front = NORTH;
        else
            this.front++;

    }
    


    // Getters and Setters

    public int getFront(){
        return this.front;
    }
    public void setFront(int front){
        this.front = front;
    }
    public int getHeuristicValue() {
    	return this.heuristicValue;
    }
    
    public void setHeuristicValue(char resultScan) {
        switch(resultScan) {
            case 'P' : this.heuristicValue = -1; break; 
            case 'N' : this.heuristicValue = 0; break; 
            case 'B' : this.heuristicValue = 0; break;
            case 'G' : this.heuristicValue = 2; break;
        }
    }
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void incrementMoves() { this.moves++; }
    public void incrementRotates() { this.rotates++; }
    public void incrementScans() { this.scans++; }
    public int getMoves() { return this.moves; }
    public int getScans() { return this.scans; }
    public int getRotates() { return this.rotates; }
    public void setMoves(int moves) { this.moves = moves; }
    public void setRotates(int rotates) { this.rotates = rotates; }
    public void setScans(int scans) { this.scans = scans; }

}
