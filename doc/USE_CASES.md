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