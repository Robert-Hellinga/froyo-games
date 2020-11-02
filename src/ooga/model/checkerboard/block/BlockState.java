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

  public void setPotentialMove() {
    isPotentialMove = true;
  }

  public void unsetPotentialMove() {
    isPotentialMove = false;
  }

  @Override
  protected BlockState clone() {
    BlockState blockState = new BlockState();
    try {
      return (BlockState) super.clone();
    } catch (CloneNotSupportedException e) {
      blockState.PlayerID = this.PlayerID;
      blockState.isEmpty = this.isEmpty;
      blockState.isKing = this.isKing;
      blockState.isChosen = this.isChosen;
      blockState.isPotentialMove = this.isPotentialMove;
    }
    return blockState;
  }
}
