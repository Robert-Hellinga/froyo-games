package ooga.view.grid;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import ooga.Coordinate;
import ooga.controller.GameController;
import ooga.controller.IGameController;
import org.apache.commons.collections.ArrayStack;

public class PieceGrid extends GridPane {

  private static final int GRID_SPACING = 4;
  private IGameController controller;
  private GridPane grid;

  public PieceGrid(IGameController controller, List<List<Integer>> initialPieceLayout) {
    this.controller = controller;
    setHgap(GRID_SPACING);
    setVgap(GRID_SPACING);

//    for (int i = 0; i < initialPieceLayout.size(); i++) {
//      for (int j = 0; j < initialPieceLayout.get(0).size(); j++) {
//        Coordinate position = new Coordinate(j, i);
//        int newState = initialPieceLayout.get(i).get(j);
//        Piece piece = new Piece(newState, position, event -> controller.clickPiece(position));
//        add(piece.getPieceShape(), j, i);
//      }
//    }

    update(initialPieceLayout);
  }

  public void update(List<List<Integer>> newPieceStates) {
//    System.out.println("k");
//    System.out.println("trying to update...");
//    getChildren().clear();

//    System.out.println(getChildren());
    System.out.print("updating grid... ");
    for (int i = 0; i < newPieceStates.size(); i++) {
      for (int j = 0; j < newPieceStates.get(0).size(); j++) {
//        System.out.print(5 + " ");
        Coordinate position = new Coordinate(j, i);
        int newState = newPieceStates.get(i).get(j);
//        System.out.print(6 + " ");
        Piece piece = new Piece(newState, position, event -> controller.clickPiece(position));
//        System.out.print(7 + " ");
        add(piece.getPieceShape(), j, i);

//        System.out.println(8 + "     ");
      }
    }
    System.out.println("updated");
  }

}
