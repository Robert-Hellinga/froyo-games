package ooga.model.checkerboard.block;

public class BlockState {

  protected int PlayerID;
  protected boolean isEmpty = false;
  protected boolean isKing;

  protected boolean isChosen;
  protected boolean isPotentialMove;

  public boolean isChosen() {
    return isChosen;
  }

  public boolean isKing() {
    return isKing;
  }

  public boolean isPotentialMove() {
    return isPotentialMove;
  }

  public void setChosen() {
    isChosen = true;
  }

  public void unsetChosen() {
    isChosen = false;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public int getPlayerID() {
    return PlayerID;
  }
}
