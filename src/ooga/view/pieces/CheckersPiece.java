//package ooga.view.pieces;
//
//import java.util.List;
//import java.util.Objects;
//import ooga.Coordinate;
//import ooga.view.newversion.PieceStructure;
//
//public class CheckersPiece extends Piece {
//
//  private boolean pieceChosen = false;
//  private boolean pieceMoved = false;
//
//  public CheckersPiece(Integer state, Coordinate coordinate) {
//    super(state, coordinate);
//  }
//
//  @Override
//  public void makePieceCanBeChosen(PieceStructure allPieces, int playerInTurn) {
//    if (state == playerInTurn) {
//      pieceShape.setOnMouseClicked(event -> choosePiece(allPieces));
//    }
//    else{
//      pieceShape.setOnMouseClicked(null);
//      pieceShape.setOnMouseClicked(null);
//    }
//  }
//
//  @Override
//  public void makePotentialMovePosClickable(PieceStructure allPieces) {
//    pieceShape.setOnMouseClicked(event -> {
//      movePieceToNewPosition(allPieces);
//    });
//  }
//
//  @Override
//  public void makePotentialMovePosUnClickable() {
//    pieceShape.setOnMouseClicked(null);
//  }
//
//  private void movePieceToNewPosition(PieceStructure allPieces){
//    for(List<Piece> pieceList : allPieces.getAllPieces()){
//      for (Piece piece : pieceList){
//        if(piece.getPieceChosen()){
//          this.setState(piece.getState());
//          updateColor();
//          piece.setState(EMPTY_STATE);
//          pieceMoved = true;
//        }
//      }
//    }
//  }
//
//  @Override
//  public void unChoosePiece() {
//    pieceShape.setStroke(Piece.UNCHOSEN_COLOR);
//    pieceChosen = false;
//  }
//
//  private void choosePiece(PieceStructure allPieces) {
//    if (checkIfThereIsPieceChosen(allPieces) != null) {
//      Objects.requireNonNull(checkIfThereIsPieceChosen(allPieces)).unChoosePiece();
//    }
//    pieceShape.setStroke(Piece.CHOSEN_STROKE_COLOR);
//    pieceChosen = true;
//  }
//
//  private Piece checkIfThereIsPieceChosen(PieceStructure allPieces) {
//    for (List<Piece> pieceList : allPieces.getAllPieces()) {
//      for (Piece piece : pieceList) {
//        if (piece.getPieceChosen()) {
//          return piece;
//        }
//      }
//    }
//    return null;
//  }
//
//  @Override
//  public void showAsPotentialMovePos() {
//    pieceShape.setFill(Piece.POTENTIAL_COLOR);
//    pieceShape.setStroke(Piece.POTENTIAL_STROKE_COLOR);
//  }
//
//
//  @Override
//  public boolean getPieceChosen() {
//    return pieceChosen;
//  }
//
//  @Override
//  public void updateColor(){
//    pieceShape.setFill(STATE_COLOR.get(state));
//    pieceShape.setStroke(Piece.UNCHOSEN_COLOR);
//  }
//
//  @Override
//  public boolean getPieceMoved() {
//    return pieceMoved;
//  }
//
//  @Override
//  public void unMovedPiece() {
//    pieceMoved = false;
//  }
//
//  @Override
//  public void setPieceChosen(boolean pieceChosen) {
//    this.pieceChosen = pieceChosen;
//  }
//
//  @Override
//  public String toString() {
//    return String.valueOf(pieceChosen);
//  }
//}
