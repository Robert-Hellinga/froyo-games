package ooga;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.View.PieceGrid;

public class Display extends Application {

  //just put some random value here
  public static final int SIZE_HEIGHT = 400;
  public static final int SIZE_WIDTH = 350;
  public static final Color BACKGROUND = Color.BLACK;
  public static final int SECOND_DELAY = 20;

  private Stage stage;
  private Scene scene;
  private GameController gameController;
  private Group root;

  public static void main(String[] args){
    launch(args);
  }

  @Override
  public void start(Stage stage){
    scene = setupScene(SIZE_WIDTH, SIZE_HEIGHT, BACKGROUND);
    stage.setScene(scene);
    stage.setTitle("Froyo Game");
    stage.show();

    gameController = new GameController("Checkers", root);

    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private Scene setupScene(int width, int height, Color background){
    root = new Group();
    Scene scene = new Scene(root, width, height, background);
    return scene;
  }

  public void step(double elapsedTime){

  }
}
