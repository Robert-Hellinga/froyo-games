package ooga.view;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.controller.GameController;
import ooga.view.screens.SplashScreen;

public class Display {

  private static final String DEFAULT_RESOURCE_BUNDLE_PATH = "ui.Display";
  private static final GameController DEFAULT_CONTROLLER = new GameController();
  private static final boolean RESIZABLE_WINDOW = false;
  private static final String WINDOW_NAME = "Froyo Games";
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

  private GameController currentController;
  private ResourceBundle resourceBundle;
  private Scene scene;
  private Stage stage;

  public Display(Stage stage) {
    this(stage, DEFAULT_CONTROLLER);
  }

  public Display(Stage stage, GameController controller) {
    this.stage = stage;
    currentController = controller;
    resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE_PATH);
    SplashScreen startScreen = new SplashScreen(resourceBundle, currentController, this);
    setNewLayout(startScreen);
    setupStage();
    setupAnimation();
  }

  private void setupStage() {
    stage.setResizable(RESIZABLE_WINDOW);
    stage.setTitle(WINDOW_NAME);
    stage.show();
  }

  private void setupAnimation() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  private void step(double elapsedTime) {
    currentController.update();
  }

  public void setNewLayout(Pane layout) {
    scene = new Scene(layout, layout.getWidth(), layout.getHeight());
    stage.setScene(scene);
  }
}
