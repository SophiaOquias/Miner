package com.mine.miner;

import com.mine.pieces.*;
import com.mine.board.*;

import java.util.*;

public class Miner {
    
    // Properties

    private int front;
    private Position currentPosition; 

    // Constructor
    public Miner(){
        this.front = 2;
        this.currentPosition = new Position(0,0);
    }

    // Methods

    public void move(){

        if(this.front == 1){
            setPosition( new Position(currentPosition.getX() - 1, currentPosition.getY()) );
        }
        else if(this.front == 2){
            setPosition( new Position(currentPosition.getX(), currentPosition.getY() + 1) );
        }
        else if(this.front == 3){
            setPosition( new Position(currentPosition.getX() + 1, currentPosition.getY()) );
        }
        else{
            setPosition( new Position(currentPosition.getX(), currentPosition.getY() - 1) );
        }
    }

    public Piece[][] scan(Quarry quarry){
        
    }

    public void rotate(){

        if(this.front == 1){
            this.front = 2;
        }
        else if(this.front == 2){
            this.front = 3;
        }
        else if(this.front == 3){
            this.front = 4;
        }
        else{
            this.front = 1;
        }

    }

    // Getters and Setters

    public int getFront(){
        return this.front;
    }
    public void setFront(int front){
        this.front = front;
    }
    public Position getPosition(){
        return this.currentPosition;
    }
    public void setPosition(Position currentPosition){
        this.currentPosition = currentPosition;
    }
}
