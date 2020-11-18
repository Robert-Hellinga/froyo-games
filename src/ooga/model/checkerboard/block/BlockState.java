package ooga.model.checkerboard.block;

public class BlockState {

  protected int PlayerID;
  protected boolean isEmpty = false;
  protected boolean isKing;
  protected boolean isChosen;
  protected boolean isPotentialMove;

  public BlockState(BlockState originalState) {
    this.PlayerID = originalState.PlayerID;
    this.isEmpty = originalState.isEmpty;
    this.isKing = originalState.isKing;
    this.isChosen = originalState.isChosen;
    this.isPotentialMove = originalState.isPotentialMove;
  }

  public BlockState() {

  }

  public void choose() {
    isChosen = true;
  }

  public void unchoose() {
    isChosen = false;
  }

  public void makePotentialMove() {
    isPotentialMove = true;
  }

  public void unmakePotentialMove() {
    isPotentialMove = false;
  }

  public void setPlayerID(int playerID) {
    PlayerID = playerID;
  }

  public int getPlayerID() {
    return PlayerID;
  }

  public void setEmpty(boolean empty) {
    isEmpty = empty;
  }

  public boolean isEmpty() {
    return isEmpty;
  }

  public void makeKing() {
    isKing = true;
  }

  public void setKing(boolean king) {
    isKing = king;
  }

  public boolean isKing() {
    return isKing;
  }

  public void setPotentialMove(boolean potentialMove) {
    isPotentialMove = potentialMove;
  }

  public boolean isPotentialMove() {
    return isPotentialMove;
  }

  public void setChosen(boolean chosen) {
    isChosen = chosen;
  }

  public boolean isChosen() {
    return isChosen;
  }

  public int getNumericState() {
    if (isEmpty() && !isPotentialMove()) {
      return 0;
    } else if (isPotentialMove()) {
      return 5;
    } else if (isKing()) {
      if (!isChosen()) {
        return getPlayerID() + 5;
      } else {
        return getPlayerID() + 7;
      }
    } else {
      if (!isChosen()) {
        return getPlayerID();
      } else {
        return getPlayerID() + 2;
      }
    }
  }
}
