package com.mine;

public class Beacon extends Piece {

    // Properties

    private int squaresToGold;

    // Constructor

    public Beacon(PotOfGold gold) {
        this.calculateSquaresToGold(gold);
    }

    // Methods

    private void calculateSquaresToGold(PotOfGold gold) {

        if(gold.pos.getX() == this.pos.getX())
            this.squaresToGold = Math.abs(gold.pos.getX() - this.pos.getX());
        else if(gold.pos.getY() == this.pos.getY())
            this.squaresToGold = Math.abs(gold.pos.getY() - this.pos.getY());

    }

}
