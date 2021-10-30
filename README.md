# CSINTSY_MCO1

## Links

- [Trello](https://trello.com/invite/b/EAAVYJhB/88219624a691f1ac516f503d380f9fb5/csintsy)
- [Lucid Chart](https://lucid.app/lucidchart/9987870d-0f44-4bb0-847f-cb79c9456357/edit?viewport_loc=447%2C-634%2C2468%2C1156%2C0_0&invitationId=inv_341e9df2-3c6a-4cc5-b0e9-7f450a1266f6)

## MC01 Specs

### Task

Design an intelligent agent that simulates a Miner  on a rectangular grid of nxn squares. We set n to be from 8 to 64.  The starting point for the Miner is always the square 1,1, on the upper left-hand corner of the grid, at row 1 and column 1. Somewhere on the grid is a unique golden square that contains the POT OF GOLD.  The agent finds its way to the golden square, and the shorter the path travelled, the more rational is the agent.

 

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

#### AGENT RATIONALITY

The locations of the pits and beacons are variables. The paths cannot be explored ahead before the miner moves. The most intelligent agent will be determined by comparing the average number of squares that were traversed to reach the golden square. 

### Design

1. It should be convenient to enter size of the grid (value for n), and to specify the locations of the pits, beacons and gold.
2. Feel free to design how your mining area would look like on screen, but by default, an nxn matrix of distinguishable squares would be minimum, that would indicate the current position of the miner and the direction of its front, as well as the locations of the pits, beacons, and pot of gold.
3. Feel free to design icons for how your miner, pits, beacons, and pot of gold will look like on screen. But the letters P, B and G would also suffice.
4. Feel free, also, to include sounds and tunes, as well as color and screen effects, to liven up the mining process.
5. Design the interface to allow a step-by-step or fast view of the miner as it moves around the mining area.
6. Include a dashboard of counters that keeps track of the number of rotates, scans, and moves that were performed. You may also include a counter for the number of “backtracks”.
7. Build at least two levels of “rational behavior”
8. Level R (Random): the miner makes random (but valid) moves, and wanders about the grid until it accidentally trips on the golden square.
9. Level S (Smart): the agent is smart that finds the “best” move given the current configuration. You can have memory of the past moves.
**Note: You may use any programming language.** 

### Deliverables

1. Source code of the project 
2. Technical Report 
3. Screen shots of the running program to describe the interface 

 

### Technical Report

Submit a pdf-copy of a written report that discusses the Problem Formulation for the agent, as well as a description of the three levels of rational behavior. The report must have a minimum of two pages (excluding the title page), single-spaced, short-bond paper, containing the following:

      I.        Introduction
In paragraph form, describe the goal of the GOLD MINER agent and formulate the problem.

     II.        AI Features
Present a flowchart of how the bot works first.
Below the flowchart, enumerate and describe in detail the AI algorithms and rules used by the bot.
   III.        Results and Analysis
Determine the specific states and configurations that the agent operates on.
Determine the specific actions, and the states (configurations) on which the actions are applicable (include illustrations for transition table).
Specify how the goal state can be determined and detected by the agent.
Discuss what situations the bot cannot handle. Explain why these situations cannot be handled by the bot. Point out which part of the Artificial Intelligence of the bot made it fail.
You may include screenshots if need be.
   IV.        Recommendations
Based on the analysis of the performance of the bot, point out the weaknesses of the bot. Identify and explain possible ways to address these weaknesses.
    V.        References (Follow the APA format)
   VI.        Appendix A: Contribution of Members
