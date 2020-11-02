package ooga.view.grid;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import ooga.Coordinate;
import ooga.controller.GameController;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.BlockState;

public class PieceGrid extends GridPane {

  private List<List<Piece>> allPieces;
  private GameController controller;

  public PieceGrid(GameController controller) {
    this.controller = controller;
    updateGrid();
  }

  public void updateGrid() {
    getChildren().clear();
    allPieces = updatePieces(controller.getAllBlockStates());

    for(int i = 0; i < allPieces.size(); i++){
      for(int j = 0; j < allPieces.get(0).size(); j++){
        Piece currentPiece = allPieces.get(i).get(j);
        add(currentPiece.getPieceShape(), j, i);
      }
    }
  }

  private List<List<Piece>> updatePieces(List<List<Integer>> newPieceStates) {
    List<List<Piece>> newPieces = new ArrayList<>();

    for (int i = 0; i < newPieceStates.size(); i++) {
      List<Piece> pieceLine = new ArrayList<>();
      for (int j = 0; j < newPieceStates.get(0).size(); j++) {
        Coordinate position = new Coordinate(j, i);
        pieceLine.add(
            new Piece(
                newPieceStates.get(i).get(j),
                position,
                event -> controller.clickPiece(position, this)
            )
        );
      }
      newPieces.add(pieceLine);
    }
    return newPieces;
  }

}
