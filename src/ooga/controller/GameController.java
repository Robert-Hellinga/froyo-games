package ooga.controller;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import ooga.Coordinate;
import ooga.model.game.Game;
import ooga.model.player.Player;

public class GameController implements IGameController {

  private static final double FRAMES_PER_SECOND = 0.4;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private static final String SKIP_ROUND_MESSAGE = " have no moves to make, will have to skip the round.";
  private static final String WON_ROUND_MESSAGE = " has won the round!";

  private boolean enableAIChecker = true;
  private Timeline animation;

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }

  private final Game game;
  private final PlayerMode mode;
  private boolean clickingEnabled;

  public GameController(Game game, PlayerMode playerMode) {
    this.game = game;
    this.mode = playerMode;
    clickingEnabled = true;
    setupAnimation();
  }

  public void checkForAITurn() {
    if (enableAIChecker) {
      if (game.getCurrentPlayerIndex() == 2 && mode == PlayerMode.PLAY_WITH_AI) {
        List<Coordinate> coords = game.getCurrentPlayer().calculateNextCoordinates();
        for (Coordinate coord : coords) {
          game.getCurrentPlayer().makePlay(coord);
        }
        setClickingEnabled(true);
        game.notifyObservers();
      }
    }
  }

  @Override
  public void clickPiece(Coordinate coordinate) {
    System.out.println("Clicking on piece... " + coordinate + " clickenabled " + clickingEnabled);
    if (clickingEnabled) {

      Player currentPlayer = game.getCurrentPlayer();
      currentPlayer.makePlay(coordinate);
      if (mode.equals(PlayerMode.PLAY_WITH_AI) && game.getCurrentPlayerIndex() == 2) {
        setClickingEnabled(false);
      }
    }
  }

  @Override
  public void setClickingEnabled(boolean enabled) {
    clickingEnabled = enabled;
  }

  @Override
  public String getGameType() {
    return game.getGameType();
  }

  private void setupAnimation() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void checkPlayerWonGame() {
    if (game.isPlayerWonGame()) {
      animation.stop();
      enableAIChecker = false;
      game.notifyObservers();
      Alert alert = new Alert(AlertType.CONFIRMATION,
          game.getCurrentPlayer().getName() + WON_ROUND_MESSAGE);
      alert.show();
    }
  }

  private void checkIfPlayerHaveNoPotentialMove() {
    if (!game.currentPlayerHavePotentialMoves() && !game.isPlayerWonGame()) {
      Alert alert = new Alert(AlertType.NONE,
          game.getCurrentPlayer().getName() + SKIP_ROUND_MESSAGE, ButtonType.OK);
      alert.show();
      game.playerTakeTurn();
      if (!game.currentPlayerHavePotentialMoves() && !game.isPlayerWonGame()) {
        Alert alert2 = new Alert(AlertType.NONE,
            game.getCurrentPlayer().getName() + SKIP_ROUND_MESSAGE, ButtonType.OK);
        alert2.show();
        game.endGame();
      }
      game.notifyObservers();
    }
  }

  private void step() {
    checkForAITurn();
    checkPlayerWonGame();
    checkIfPlayerHaveNoPotentialMove();
    game.notifyObservers();
  }
}
