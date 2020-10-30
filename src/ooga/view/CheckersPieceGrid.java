package ooga.view;

import java.util.List;
import javafx.scene.Group;
import ooga.view.pieces.Piece;

public class CheckersPieceGrid extends PieceGrid{

  public CheckersPieceGrid(String gameType, PieceStateStructure initiationPiecesState, Group root){
    super(gameType, initiationPiecesState, root);
  }

  @Override
  public void updatePieceGrid() {
    updatePieceClickableStatus();
  }

  private void updatePieceClickableStatus(){
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for(Piece piece : pieceList){
        piece.makePieceClickable(allPieces);
      }
    }
  }


}
