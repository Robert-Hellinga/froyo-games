* User starts program, chooses game of checkers against AI
```java
  Display display = new Display();
  //User clicks on Checkers button,
  GridGame game = new Checkers(HumanPlayer, CheckersAI); //made in controller
```  
  
* User clicks on grid to move checker piece
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

* User starts a new game of Connect 4 against another random person
```java
    // User clicks on connect 4 button and 
    Player user = new HumanPlayer();
    Player opponent = new HumanPlayer("Name of player");
    GridGame game = new Connect4Game(user, opponent);
```  

* User loses a game of connect 4
```java
    Point[] possibleMoves = Connect4Controller.getPossibleMoves();
    CheckersUI.showPossibleMoves(possibleMoves);
    // User clicks on one of possible moves
    Connect4Controller.movePiece(ColumnPoint);
    //controller makes call to connect 4 game
    Connect4Game.makeMove(ColumnPoint);
    Connect4Game.checkForWin();
    // make move notifies observers, which calls following
    Connect4UI.update();
    
```  

 * Selecting username
```java
    Display display = new Display();
    Player humanPlayer = new HumanPlayer("Name of player");
    // Start game button is pressed
    Player remotePlayer = new HumanPlayer("Name of remote player");
    GridGame game = new Checkers(humanPlayer, remotePlayer); //made in controller
    
```  

 * AI makes a move
```java
    GridGame.takeTurn(); // takeTurn method handles turn alternation

    ArtificialPlayer.calculateTurn();
    ArtificialPlayer.update(); // update method causes GridGame to update observers
``` 

 * Selecting a game theme
```java
    // in Display.java, have dropdown to set theme
    Dropdown themeDropdown = new Dropdown(String[] themes, event -> setTheme(themeDropdown.getValue()));

    // setTheme method
    public void setTheme(String stylesheet) {
      GameUI.setTheme(stylesheet);
      SocialUI.setTheme(stylesheet);
    }
``` 

 * Selecting a game type on initial splash screen
```java
    // in Display.java, have three buttons to choose different games
    Button othello = new Button(OTHELLO_BTN, event -> setCurrentUI(new OthelloUI()));
    Button checkers = new Button(CHECKERS_BTN, event -> setCurrentUI(new CheckersUI()));
    Button connect4 = new Button(CONNECT4_BTN, event -> setCurrentUI(new Connect4UI()))

    // makeUI method
    public void setCurrentGame(GameUI game) {
      this.GameUI = game;
      this.controller = new ____Controller(); // USE REFLECTION HERE DEPENDING ON GAME toString()
    }
``` 

 * Dropping a piece in Connect 4 game
```java
    // When initializing game
    squares[i][j] = new Connect4Square(i, j, event -> Connect4Controller.clickOnColumn(j));

    // in Connect4Controller
    public void clickOnColumn(j) {
      if(isPlayerTurn) {
        Connect4Game.makeMove(j);
      }
      else {
        Display.showError(new OutOfTurnException("Not your turn!"));
      }
    }
``` 
