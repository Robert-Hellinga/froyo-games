package ooga.view.grid;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import ooga.Coordinate;
import ooga.controller.GameController;
import ooga.controller.IGameController;

public class PieceGrid extends GridPane {

  private IGameController controller;

  public PieceGrid(IGameController controller, List<List<Integer>> initialPieceLayout) {
    this.controller = controller;
    update(initialPieceLayout);
  }

  public void update(List<List<Integer>> newPieceStates) {
    getChildren().clear();

    for (int i = 0; i < newPieceStates.size(); i++) {
      for (int j = 0; j < newPieceStates.get(0).size(); j++) {
        Coordinate position = new Coordinate(j, i);
        int newState = newPieceStates.get(i).get(j);
        Piece piece = new Piece(newState, position, event -> controller.clickPiece(position));
        add(piece.getPieceShape(), j, i);
      }
    }
  }

}
