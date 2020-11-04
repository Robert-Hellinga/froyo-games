package ooga.view.grid;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import ooga.Coordinate;
import ooga.controller.GameController;
import ooga.controller.IGameController;

public class PieceGrid extends GridPane {

  private List<List<Piece>> allPieces;
  private IGameController controller;

  public PieceGrid(IGameController controller, List<List<Integer>> initialPieceLayout) {
    this.controller = controller;
    updateGrid(initialPieceLayout);
  }

  public void updateGrid(List<List<Integer>> newPieceStates) {
    getChildren().clear();
    allPieces = updatePieces(newPieceStates);

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
                event -> controller.clickPiece(position)
            )
        );
      }
      newPieces.add(pieceLine);
    }
    return newPieces;
  }

}
