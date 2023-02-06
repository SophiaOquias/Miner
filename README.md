# Miner

### Description

An intelligent agent that simulates a Miner  on a rectangular grid of nxn squares. We set n to be from 8 to 64.  The starting point for the Miner is always the square 1,1, on the upper left-hand corner of the grid, at row 1 and column 1. Somewhere on the grid is a unique golden square that contains the *Pot of Gold*.  There are two types of miners, a Random Miner and a Smart Miner. The Random Miner utilizes Depth First Search to find the Pot of Gold, while the Smart Miner utilizes Best First Search to find the Pot of Gold.  

 

### Setup

#### MINING AREA

Each location (square) in the mining area (the nxn grid) can have only one of the following objects:

- the miner
- the pot of gold (G)
- a pit (P)
- a beacon (B)

#### FRONT

The miner has a front, indicating the direction of the actions scan and move. The miner moves around the mining area by displacing itself one square to the front, unless it is over the edge of the grid, which should be programmed as a technical “impossibility”.

#### SCAN

To view what is in front of the miner all the way to the edge of the mining area, the miner uses the action scan. The scan action returns only one of the following : p for pit, g for gold, b for beacon, depending on which one of these is nearest to the miner. The distance of the miner to the object is not returned. If there is no pit, beacon nor gold in all the squares in front of the miner, then the action scan returns “NULL”. 

#### ROTATE

The direction of its front can be altered through the action rotate, which moves 90 degrees clockwise each time rotate is invoked.

#### PIT

If the miner moves into square that is a pit, it is game-over! Remember that the miner cannot see the locations of the pits.

#### BEACON

A beacon on a square indicates that from that square, the golden square can be reached in m squares in any vertical or horizontal direction, where m < n, without ever falling into a pit. The value of m is not returned by the action scan.

#### GOLDEN SQUARE

If the miner moves into square that contains the pot of gold,   the miner stops, and delivers a “search successful” message. 
