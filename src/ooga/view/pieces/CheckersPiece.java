package ooga.view.pieces;

import java.util.List;
import java.util.Objects;
import ooga.view.PieceStructure;

public class CheckersPiece extends Piece {

  private boolean pieceChosen = false;

  public CheckersPiece(Integer state, Integer xCoordinate, Integer yCoordinate){
    super(state, xCoordinate, yCoordinate);
  }

  @Override
  public void makePieceClickable(PieceStructure allPieces) {
    if (state != 0){
      pieceShape.setOnMouseClicked(event -> choosePiece(allPieces));
    }
  }

  @Override
  public void unChoosePiece(){
    pieceShape.setStroke(Piece.UNCHOSEN_COLOR);
    pieceChosen = false;
  }

  private void choosePiece(PieceStructure allPieces){
    if (checkIfThereIsPieceChosen(allPieces) != null){
      Objects.requireNonNull(checkIfThereIsPieceChosen(allPieces)).unChoosePiece();
    }
    pieceShape.setStroke(Piece.CHOSEN_COLOR);
    pieceChosen = true;
  }

  private Piece checkIfThereIsPieceChosen(PieceStructure allPieces){
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for(Piece piece : pieceList){
        if (piece.getPieceChosen()){
          return piece;
        }
      }
    }
    return null;
  }


  @Override
  public boolean getPieceChosen(){
    return pieceChosen;
  }

}
