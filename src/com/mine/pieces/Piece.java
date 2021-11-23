package com.mine.pieces;

public abstract class Piece {
	
    // Properties

    protected Position pos;
    
    // Methods

    public Position getPosition() { 
        return this.pos; 
    }

    public void setPosition(Position pos) { 
        this.pos = pos;
    }

}
