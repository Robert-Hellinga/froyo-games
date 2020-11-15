package ooga.controller;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ooga.Coordinate;
import ooga.fileHandler.Database;
import ooga.model.game.Game;
//import ooga.model.player.Player.PlayerType;
import ooga.model.player.Player;
import ooga.view.grid.PieceGrid;

public class GameController implements IGameController {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final double AI_DELAY = 0.05;

  private double delayCounter = AI_DELAY;

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }

  private PieceGrid pieceGrid;
  private Game game;
  private int playerInTurn;
  private Coordinate pieceChosen;
  private List<Player> allPlayers;
  private PlayerMode mode;

  public GameController(Game game, PlayerMode playerMode) {
    this.game = game;
    this.mode = playerMode;
    allPlayers = game.getAllPlayers();
    setupAnimation();
  }


  public void setGameType(String gameType) {

  }

  public void checkForAITurn(double elapsedTime) {
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

  @Override
  public void createGame() {

  }

  @Override
  public void clickPiece(Coordinate coordinate) {
      Player currentPlayer = game.getCurrentPlayer();
      currentPlayer.makePlay(coordinate);
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
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void step(double elapsedTime) {
    checkForAITurn(elapsedTime);
  }
}
