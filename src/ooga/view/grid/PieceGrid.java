package ooga.view.grid;

import java.util.List;
import javafx.scene.layout.GridPane;
import ooga.Coordinate;
import ooga.controller.IGameController;

/**
 * Class containing view elements for displaying the game pieces
 * @author Nate Mela (nrm27)
 */
public class PieceGrid extends GridPane {

  private static final int GRID_SPACING = 4;
  private final IGameController controller;

  public PieceGrid(IGameController controller, List<List<Integer>> initialPieceLayout) {
    this.controller = controller;
    update(initialPieceLayout);
    setHgap(GRID_SPACING);
    setVgap(GRID_SPACING);
    update(initialPieceLayout);
  }

  /**
   * Method called periodically by observer in GameScreen to update the grid with new piece states
   */
  public void update(List<List<Integer>> newPieceStates) {
    getChildren().clear();

    for (int i = 0; i < newPieceStates.size(); i++) {
      for (int j = 0; j < newPieceStates.get(0).size(); j++) {
        Coordinate position = new Coordinate(j, i);
        int newState = newPieceStates.get(i).get(j);
        Piece piece = new Piece(newState, position, event -> controller.clickPiece(position),
            controller.getGameType());
        add(piece.getPieceShape(), j, i);
      }
    }
  }

}
