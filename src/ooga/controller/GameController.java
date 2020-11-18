package ooga.controller;

import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import ooga.Coordinate;
import ooga.fileHandler.Database;
import ooga.model.game.Game;
import ooga.model.player.Player;

public class GameController implements IGameController {

  public static final double FRAMES_PER_SECOND = 0.2;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final double AI_DELAY = 0.05;
  public static final String SKIP_ROUND_MESSAGE = " have no moves to make, will have to skip the round.";

  private double delayCounter = AI_DELAY;
  private boolean enableAIChecker = true;
  private Timeline animation;

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }

  private final Game game;
  private List<Player> allPlayers;
  private final PlayerMode mode;
  private boolean clickingEnabled;

  public GameController(Game game, PlayerMode playerMode) {
    this.game = game;
    this.mode = playerMode;
    clickingEnabled = true;
    allPlayers = game.getAllPlayers();
    setupAnimation();
  }

  public void setGameType(String gameType) {

  }

  public void checkForAITurn(double elapsedTime) {
    if (enableAIChecker){
      if (game.getCurrentPlayerIndex() == 2 && mode == PlayerMode.PLAY_WITH_AI){
        if (delayCounter - elapsedTime < 0){
          List<Coordinate> coords = game.getCurrentPlayer().calculateNextCoordinates();
          for(Coordinate coord : coords){
            game.getCurrentPlayer().makePlay(coord);
          }
          delayCounter = AI_DELAY;
        }
        else{
          delayCounter -= elapsedTime;
        }
      }
    }
  }

  @Override
  public void createGame() {

  }

  @Override
  public void clickPiece(Coordinate coordinate) {
    if(clickingEnabled) {
      Player currentPlayer = game.getCurrentPlayer();
      currentPlayer.makePlay(coordinate);
    }
  }

  @Override
  public void setClickingEnabled(boolean enabled) {
    clickingEnabled = enabled;
  }

  @Override
  public void saveGame() {

  }

  @Override
  public void restartGame() {

  }

  @Override
  public void quitGame() {

  }

  private void setupAnimation() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void checkPlayerWonGame(){
    if (game.isPlayerWonGame()){
      System.out.println(game.getWinningPlayer().getName() + " has won the game!");
      enableAIChecker = false;
    }
  }

  private void checkIfPlayerHaveNoPotentialMove(){
    if (game.isHaveNoPotentialMove()){
      Alert alert = new Alert(AlertType.NONE, game.getCurrentPlayer().getName() + SKIP_ROUND_MESSAGE, ButtonType.OK);
      alert.show();
      game.playerTakeTurn();
      if (!game.currentPlayerHavePotentialMoves()){
        Alert alert2 = new Alert(AlertType.NONE, game.getCurrentPlayer().getName() + SKIP_ROUND_MESSAGE, ButtonType.OK);
        alert2.show();
        game.endGame();
      }
      game.resetHaveNotPotentialMove();
    }
  }

  private void step(double elapsedTime) {
    checkForAITurn(elapsedTime);
    game.notifyObservers();
    checkPlayerWonGame();
    checkIfPlayerHaveNoPotentialMove();
  }
}
