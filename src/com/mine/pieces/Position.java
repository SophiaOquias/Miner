package com.mine.pieces;

public class Position {
	
	//Properties
	private int x;
	private int y;
	
	//Constructor
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	 @Override
	    public boolean equals(Object object)
	    {
	        boolean sameSame = false;

	        if (object != null && object instanceof Position)
	        {
	            sameSame = this.x == ((Position) object).x && this.y == ((Position) object).y ;
	        }

	        return sameSame;
	    }
	
	//Methods
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int input) {
		this.x = input;
	}
	
	public void setY(int input) {
		this.y = input;
	}

}
