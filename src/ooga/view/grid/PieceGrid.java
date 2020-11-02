package ooga.view.grid;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import ooga.Coordinate;
import ooga.controller.GameController;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.BlockState;

public class PieceGrid extends GridPane {

  private List<List<Piece>> allPieces;
  private GameController controller;

  public PieceGrid(GameController controller) {
    this.controller = controller;
    setAllPiecesToGrid();
  }

  private void setAllPiecesToGrid() {
    allPieces = initiateAllPieces(controller.getAllBlockStates());

    for(int i = 0; i < allPieces.size(); i++){
      for(int j = 0; j < allPieces.get(0).size(); j++){
        Piece currentPiece = allPieces.get(i).get(j);
        add(currentPiece.getPieceShape(), j, i);
      }
    }
  }

  private List<List<Piece>> initiateAllPieces(List<List<Integer>> initialBlockStates) {
    List<List<Piece>> initialPieces = new ArrayList<>();

    for (int i = 0; i < initialBlockStates.size(); i++) {
      List<Piece> pieceLine = new ArrayList<>();
      for (int j = 0; j < initialBlockStates.get(0).size(); j++) {
        Coordinate position = new Coordinate(j, i);
        pieceLine.add(
            new Piece(
                initialBlockStates.get(i).get(j),
                position,
                event -> controller.clickPiece(position)
            )
        );
      }
      initialPieces.add(pieceLine);
    }
    return initialPieces;
  }

}
