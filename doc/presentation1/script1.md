* what genre or game are you focusing on and what commonalities and differences have you identified
    * Strategy Games
    * Commonalities
        * Two players
        * Can use minimax algo for AI on them
        * Use a grid, players place pieces on said grid
    * Differences
        * Win condition
        * Potential moves
            * Checkers: Diagonal only, jumping
            * Connect4: Gravity, piece falls to bottom of column
            * Othello: Can only place in a position that "outflanks" opponent
* what features (including optional ones) you are planning to work on
    * Home screen that lets you pick which game and what type of opponent
    * Play online with other players
        * Either can put in a name and match with that player to play them, or randomly
    * AI players
    * Start the game over against AI (resets everything in current game)
    * Save the game
        * Saves state of board and whose turn it is, so that game can be resumed later
    * Change the look/theme of the game
        * Using CSS files
* who is expected to work on which parts of the project
     * Nate: 
        * Primary: Front-end/View, social
        * Secondary: Model
     * Yixuan: 
        * Primary: Model, AI
        * Secondary: Front-end/View
     * Jincheng: 
        * Primary: Model, AI
        * Secondary: Front-end/View
     * Robert: 
        * Primary: Configuration, AI
        * Secondary: Controller
     * Lucas: 
        * Primary: Controller
        * Secondary: Model
* what features you expect to complete during each of the three Sprints
    * Sprint 1
        * Basic UI
            * Add a start screen/menu
            * Add a gameplay window
        * Create model functionality for Connect 4 and Checkers
        * Create controller and connect Model with View by end of sprint
    * Sprint 2
        * Othello
        * AI implemented
    * Sprint 3
        * Social working
        * double-check/fix design problems
* demo the User Interface Wireframe to show how the user will interact with the program
* demo any working code you have completed during the week 


* describe the design and framework goals: what is expected to be flexible/open and what is fixed/closed
    * Flexible:
        * Adding different types of games and being able to access them from splash screen
            * Would have to add more functionality to play non-grid game
        * Add games that use a grid as their board
        * Change how AI works
    * Fixed:
        * Can't change language once program has started
        * Limited to 2 player games with current plan for controller/game model
* provide an overview of the project's modules: what is each responsible for, how does it depend on other modules, what does it encapsulate, and how can new features be plugged in to it
    * Display
        * Hold all UI elements
        * Takes in different UI elements (like Game UI) and displays them
    * GridGame class
        * Lets you have a grid with pieces at certain locations
        * Can move pieces depending on rules of subclass
        * Can add new subclasses for different rules
    * Abstract Player
        * Can either be human or AI (AI has specific subtypes for each game)
        * Get move decision
            * Either from user input or AI
* describe two APIs in detail:
    * what service does it provide?
        * GridGame
            * Check for win
            * Take turn
                * Move piece
            * Notify observers
    * how does it provide for extension?
        * Can add different games
        * Can use grid for other purposes
        * Could use splash screen/display for variety of things
    * how does it support users (your team mates) to write readable, well design code?
        * Open to extension
        * Lays out framework in advance so we know which methods we need to implement to have whole program function together
* describe two Use Cases in detail that show off how to use each of the APIs described above
   * User starts a game of checkers against AI player
```java
  Display display = new Display();
  //User clicks on Checkers button,
  GridGame game = new Checkers(Player.Player.HumanPlayer, CheckersAI); //made in controller
```  
   * User makes a move on checker board
```java
    Point[] possibleMoves = CheckersController.getPossibleMoves(Point);
    CheckersUI.showPossibleMoves(possibleMoves);
    // User clicks on one of possible moves
    CheckersController.movePiece(Point start, Point end);
    //controller makes call to checkers game
    CheckersGame.makeMove(Point start, Point end);
    // make move notifies observers, which calls following
    CheckersUI.update();
```  
* describe one alternative design considered and what trade-offs led to it not being chosen
    * Composition vs Inheritance for grid games