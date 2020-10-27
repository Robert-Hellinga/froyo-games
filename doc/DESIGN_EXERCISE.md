# OOGA Lab Discussion
## Names and NetIDs
Nate Mela (nrm27)
Robert Hellinga (rbh13)
Lucas Carter (lbc21)
Jincheng He (jh622)
Yixuan Li (yl600)

## Fluxx

### High Level Design Ideas
Generic Card class, used by specific types of Cards (RuleCard, GoalCard, ActionCard).
Generic attribute class, used to set different rules/goals/actions for each type of card. Child
 RuleAttribute, GoalAttribute, ActionAttribute
Dealer – class used if there is a dealer to deal cards
Board – class used to manage current round states, and cycle turns
Player.Player – class used by client to interact with the Fluxx game

### CRC Card Classes

This class's purpose is to be a generic card object:
```java
 public abstract class Card {
     public abstract void playCard();
     public abstract void getCardValue();
     public abstract void setCardAttribute(Atrribute atrribute);
 }
```

This class's purpose or value is to be useful:
```java
 public class RuleCard extends Card {
     public void playCard();
     public void getCardValue();
     public void setCardAttribute(Attribute atrribute);
     public void setGameRule(Rule rule);
 }
```

```java
 public class Dealer implements Playable {
     public void cycleTurn(); // from interface
     public void changeGameRule(Rule rule);
     public void changeGoal(Goal goal);
     private boolean gameOver();
 }
```

```java
 public class Goal {
    public Goal(Attribute attribute);
 }
```

```java
 public class Player.Player implements Playable {
     private List<Card> hand;
     public void cycleTurn(); //from interface
 }
```

```java
 import Player.Player;public class Board implements Playable {
     private List<Card> activeCards;
     private List<Player> players;
     private void initializePlayers();
     public void cycleTurn(); //from interface
     private void displayWinner();
     // holds buttons/controls for UI
 }
```


### Use Cases

### Use Cases

 * A new game is started with two players
 ```java
Board board = new Board(2);
board.initializePlayers();
 ```

 * Player.Player 1 plays a rule card
 ```java
// Board has step method, which calls Player.Player cycleTurn, which calls Card's playCaerd
Board.step(); 
RuleCard newRule = new RuleCard();
newRule.setAttribute(A);
player.getCard().playCard(newRule);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
Board.step()
if(dealer.gameOver()) 
  Board.displayWinner();
 ```

 * A new Goal is added to the game and the win criteria is changed.
 ```java
Goal newGoal = new Goal(Rule rule);
Dealer.changeGoal(newGoal);
 ```