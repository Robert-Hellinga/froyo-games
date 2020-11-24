# Final Project Design
### Names
- Robert Hellinga
- Lucas Carter
- Nate Mela
- Yixuan Li
- Jincheng He

## Team Roles and Responsibilities

* Nate
	* Week 1: View framework
	* Week 2: View with CSS, buttons, graphics
	* Week 3: Implementing/debugging online play

* Lucas
	* Week 1: Controller framework
	* Week 2: FroyoController/GameController, observers, 
	* Week 3: model Player refactor, SocialController, debugging/refactoring for online play

* Robert
	* Week 1: FileReader, FileException, ResourceException
	* Week 2: States model refactor pt 1: Piece, untangling BlockConfigStructure
	* Week 3: States model refactor pt 2: color resource files for games, untangling
	BlockState, making states subclass-specific/open to extension
	
* Jincheng
    * Week 1: Temp model framework, API and use cases CheckersGame, CheckersBlockGrid
    * Week 2: model refactoring, Model dependencies, AI: Game, Connect4Game, etc, Connect4AI framework, Player framework
    * Week 3: OthelloAI, enable player skipping round

*Yixuan
    * Week 1: Design Wireframe, Temp model framework, API and use cases for Block, CheckersBlock, BlockState,BlockConfigStructure
    BlockStructure
    * Week 2: model refactoring, Model dependencies, AI: Conncet4 Blocks, etc, CheckersAI framework, Player framework, 
    * Week 3: Othello game, othello block, check player winning game


## Design goals

#### What Features are Easy to Add

* new game that is also grid-based

* more player playing the game, if it is achievable in the game

## Assumptions that Affect the Design
* Grid is a 2d list of lists, stored in BlockStructure
* Empty blocks are always state 0, player1's first piece is always 1,
player2's first piece is always 2, piece states ideally alternate between 
player1 (odd) and player2(even)

#### Features Affected by Assumptions

* The grid must be in the shape of a rectangle

* Games with asymmetrical piece types (i.e. 
one player controls 3 types of monsters, other player
controls 7 types of soldiers) may have logical errors


## Significant differences from Original Plan

* The AIBrain was not considered when we first made the plan


## New Features HowTo

#### Easy to Add Features

* add new game that also grid-piece based
   
    * Create a block class extending the block abstraction, create a blockgrid class extending the 
    blockgrid abstraction, and then create a game class that extends the game abstraction. Implement
     the respective game rule in these classes. 

#### Other Features not yet Done

* Ranking system: leaderboards/save games locally (scrapped)