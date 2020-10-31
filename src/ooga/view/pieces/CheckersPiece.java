package ooga.view.pieces;

import java.util.List;
import java.util.Objects;
import ooga.Coordinate;
import ooga.view.PieceStructure;

public class CheckersPiece extends Piece {

  private boolean pieceChosen = false;

  public CheckersPiece(Integer state, Coordinate coordinate) {
    super(state, coordinate);
  }

  @Override
  public void makePieceClickable(PieceStructure allPieces, int playerInTurn) {
    if (state == playerInTurn) {
      pieceShape.setOnMouseClicked(event -> choosePiece(allPieces));
    }
  }

  @Override
  public void unChoosePiece() {
    pieceShape.setStroke(Piece.UNCHOSEN_COLOR);
    pieceChosen = false;
  }

  private void choosePiece(PieceStructure allPieces) {
    if (checkIfThereIsPieceChosen(allPieces) != null) {
      Objects.requireNonNull(checkIfThereIsPieceChosen(allPieces)).unChoosePiece();
    }
    pieceShape.setStroke(Piece.CHOSEN_STROKE_COLOR);
    pieceChosen = true;
  }

  private Piece checkIfThereIsPieceChosen(PieceStructure allPieces) {
    for (List<Piece> pieceList : allPieces.getAllPieces()) {
      for (Piece piece : pieceList) {
        if (piece.getPieceChosen()) {
          return piece;
        }
      }
    }
    return null;
  }

  @Override
  public void showAsPotentialMovePos() {
    pieceShape.setFill(Piece.POTENTIAL_COLOR);
    pieceShape.setStroke(Piece.POTENTIAL_STROKE_COLOR);
  }

  public void showAsUnPotentialMovePos() {
    pieceShape.setFill(STATE_COLOR.get(state));
    pieceShape.setStroke(STATE_COLOR.get(state));
  }

  @Override
  public boolean getPieceChosen() {
    return pieceChosen;
  }

}
