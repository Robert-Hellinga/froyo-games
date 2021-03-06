 * Introduction
    * Our team has decided to create an application which plays a collection of strategy games
    * Design goals:
        * Have a very abstract implementation of one single game, which can then be extended and
         modified to work with other games
    * High-level design:
        * Model: contains inheritance hierarchy with very abstract game at the top, contains AI
         functionality
        * View: contains all UI elements
        * Controller: contains controller for interaction between model and view, contains
         interaction with web server for social elements
 
 * Overview - (ROUGH DRAFT, VIEW UML FOR NEWER VERSION)
    * Model:
        * Playable: startGame(), saveGame()
        * Socialable?
        * GridGame: checkForWin(), contains 2D array of Cell objects
            * Connect4Game 
            * OthelloGame
            * CheckersGame
        * AI
        * Util
    * View:
        * Languagable Interface
        * Styleable Interface
        * Display
        * GameUI
            * OthelloUI
            * CheckersUI
            * Connect4UI
        * SocialUI
        * SplashScreen
        * UI Elements
            * Button
            * Dropdown
            * Popup
        * Util
    * Controller:
        * Controller
        * SocialController: getPlayers(), getOtherPlayerMove()
        * DataReader: reads saves and configuration files
        * DataWriter: writes saves to files
        
 * Design Details 
    * GridGame: Generic model game class that is extended to implement game-specific calculations
     and functionality
    * AI: Class used to calculate single-player opponent moves using AI
    * GameUI: Generic view class that is extended to implement the specific UI for each game
    * SocialUI: UI class used for social functionality
    * Display: primary UI class, try to keep as small as possible
    * Controller: Class used by Display to manipulate the different game types within model. Will
     have a field containing the current GridGame being run
    * SocialController: Class used to interact with web server for multiplayer game mode
 
 * Example games
    * Connect 4 ??? can only pick a column to place pieces, win condition is 4 in a row diagonally
    /horizontally/vertically.
    * Othello ??? can place disks to flank opponent's disks. Game ends when there are no more
     available moves. Winner is determined by whoever has more disks showing in their color
    * Checkers ??? can move checkers diagonally, can jump over opponent checkers to take them off
     the board. Win condition is taking all opponents checkers off the board
 * Design Considerations 
    * Issue: Figure out how to work with web server/API
    * Issue: player type selection (AI vs. human)
        * Discussion was about whether we would have Player.Player objects or Socialable interface
        . Decision will be made after farther determining how web server will work
    * To-do: decide on data storage format