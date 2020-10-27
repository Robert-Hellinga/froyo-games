* User starts program, chooses game of checkers against AI
```java
  Display display = new Display();
  //User clicks on Checkers button,
  GridGame game = new Checkers(Player.Player.HumanPlayer, CheckersAI); //made in controller
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
    Player.Player user = new Player.Player.HumanPlayer();
    Player.Player opponent = new Player.Player.HumanPlayer("Name of player");
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
    Player.Player humanPlayer = new Player.Player.HumanPlayer("Name of player");
    // Start game button is pressed
    Player.Player remotePlayer = new Player.Player.HumanPlayer("Name of remote player");
    GridGame game = new Checkers(humanPlayer, remotePlayer); //made in controller
    
```  

 * AI makes a move
```java
    GridGame.takeTurn(); // takeTurn method handles turn alternation

    Player.Player.ArtificialPlayer.calculateTurn();
    Player.Player.ArtificialPlayer.update(); // update method causes GridGame to update observers
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

  * select a piece in Checker's game when it is your turn 
```java
    //When initializing game 
    squares[i][j] = new CheckersSquare(i, j, event -> CheckersController.clickOnBlock(i, j));
    
    //in CheckerController
    public void clickOnBlock(int i, int j) {
        if (isPlayerTurn){
            if (squares[i][j].hasPiece()){
                squares[i][j].choosePiece();
            }
        }
        else {
            CheckersController.showError("Not your turn!");
        }
    }
```  

  * after the player select a piece in Checker's game, show possible moves it can make 
  ``` java
    public enmu Side {
      UP,
      DOWN
    }

    public void showPossibleMove(){
      for (int x = 0; x < i; x ++){
        for (int y = 0; y < j; y++){
          if (square[i][j].isChoosen()){
            if (player.getSide == Side.DOWN){
              square[i+1][j-1].becomePossibleMove();
              square[i+1][j+1].becomePossibleMove();
            }
            else if (player.getSide == Side.UP){
              square[i-1][j-1].becomePossibleMove();
              square[i-1][j+1].becomePossibleMove();
            }
          }
        }
      }
    }
  ```

  * move the piece to a possible location in checker game
  ```java
  public void moveToPossibleLocation(int i, int j){
    if (square[i][j].isPossibleLocation()){
      Cell choosenCell = findSelectedCell(int i, int j);
      squre[i][j].movePiece(choosenCell);
      choosenCell.becomeaBlankBlock();
    }
  }

  ```
  * Save game and quit to homepage 
  ```java
  //this method will save current game and configure all information to a data file
  saveCurrentGame();    
  //this method will add homepage to root   
  showHomePage();
```

  * restart the game 
  ```java
  loadInitialPattern();
  resetTimer();
  ```

