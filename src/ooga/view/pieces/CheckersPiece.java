package ooga.view.pieces;

public class CheckersPiece extends Piece {

  private boolean pieceChosen = false;

  public CheckersPiece(Integer state, Integer xCoordinate, Integer yCoordinate){
    super(state, xCoordinate, yCoordinate);
    makePieceClickable();
  }

  @Override
  protected void makePieceClickable() {
    if (state != 0){
      pieceShape.setOnMouseClicked(event -> pieceChosen());
    }
  }

  private void pieceChosen(){
    pieceShape.setStroke(Piece.CHOSEN_COLOR);
    pieceChosen = true;
  }


}
