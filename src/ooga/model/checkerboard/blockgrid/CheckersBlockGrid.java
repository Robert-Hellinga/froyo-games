package ooga.model.checkerboard.blockgrid;

import java.util.List;
import ooga.Coordinate;
import ooga.model.checkerboard.block.CheckersBlock;
import ooga.model.game.CheckersGame;

public class CheckersBlockGrid extends BlockGrid {

  public CheckersBlockGrid(String gameType, List<List<Integer>> allBlockConfig, int numPlayers) {
    super(gameType, allBlockConfig, numPlayers);
  }

  public CheckersBlockGrid(BlockGrid checkBoard) {
    super(checkBoard);
  }


  /**
   * set all the available positions in a checkers game
   * @param currentPlayerIndex the index of the current player
   * @param chosenBlock the coordinate of the chosen block
   */

  @Override
  public void setAvailablePositions(int currentPlayerIndex, Coordinate chosenBlock) {
    if (!chosenBlock.equals(Coordinate.INVALID_COORDINATE)) {
      for (Coordinate coordinate :
          allBlocks.getBlock(chosenBlock).getAvailablePositions(currentPlayerIndex, allBlocks)) {
        allBlocks.getBlock(coordinate).makePotentialMove();
      }
    }
  }


  /**
   * play checkers game
   * @param passInCoordinate the given coordinate
   * @param currentPlayerIndex the index of the current player
   */

  @Override
  public void play(Coordinate passInCoordinate, Integer currentPlayerIndex) {
    if (hasChosenBlock()) {
      if (allBlocks.getBlock(passInCoordinate).getPlayerID()
          == currentPlayerIndex) {
        unChooseAllBlocks();
        unsetAllBlockPotentials();
        getAllBlocks().getBlock(passInCoordinate).choose();
        setAvailablePositions(currentPlayerIndex, passInCoordinate);
      } else if (allBlocks.getBlock(passInCoordinate).isPotentialMove()) {

        removeCheckedPiece(passInCoordinate, getChosenBlockCoordinate());
        moveBlock(getChosenBlockCoordinate(), passInCoordinate);
        makeBlockKing(passInCoordinate);
        unChooseAllBlocks();
        unsetAllBlockPotentials();
        getAllBlocks().getBlock(passInCoordinate).setPlayerID(currentPlayerIndex);
        finishARound = true;
      }
    } else {
      if (!allBlocks.getBlock(passInCoordinate).getIsEmpty()
          && allBlocks.getBlock(passInCoordinate).getPlayerID()
          == currentPlayerIndex) {
        allBlocks.getBlock(passInCoordinate).choose();
        setAvailablePositions(currentPlayerIndex, passInCoordinate);
      }
    }
  }

  /**
   * remove a checked piece
   * @param newPosition the new coordinate of the piece
   * @param originalPosition the original coordinate of the piece
   */

  public void removeCheckedPiece(Coordinate newPosition, Coordinate originalPosition) {
    int xMovement = newPosition.xCoordinate() - originalPosition.xCoordinate();
    int yMovement = newPosition.yCoordinate() - originalPosition.yCoordinate();
    if (Math.abs(xMovement) == 2 && Math.abs(yMovement) == 2) {
      Coordinate pieceToRemoveCoordinate = new Coordinate(
          originalPosition.xCoordinate() + xMovement / 2,
          originalPosition.yCoordinate() + yMovement / 2
      );
      crossPiece(pieceToRemoveCoordinate);
    }
    if (Math.abs(xMovement) > 2 || Math.abs(yMovement) > 2) {
      for (int xIndicator : List.of(-2, 2)) {
        boolean breakLoop = false;
        for (int yIndicator : List.of(-2, 2)) {
          if (newPosition.xCoordinate() + xIndicator > 0
              && newPosition.xCoordinate() + xIndicator < allBlocks
              .getBlockStructureWidth() - 1 && newPosition.yCoordinate() + yIndicator > 0
              && newPosition.yCoordinate() + yIndicator < allBlocks
              .getBlockStructureHeight() - 1) {
            Coordinate interCoordinate = new Coordinate(newPosition.xCoordinate() + xIndicator,
                newPosition.yCoordinate() + yIndicator);
            if (allBlocks.getBlock(interCoordinate).isPotentialMove()) {
              removeCheckedPiece(interCoordinate, originalPosition);
              crossPiece(new Coordinate(newPosition.xCoordinate() + xIndicator / 2,
                  newPosition.yCoordinate() + yIndicator / 2));
              breakLoop = true;
              break;
            }
          }
        }
        if (breakLoop) {
          break;
        }
      }
    }
  }

  /**
   * get the coordinate of the chosen block
   * @return the coordinate of the chosen block
   */

  public Coordinate getChosenBlockCoordinate() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).isChosen()) {
          return new Coordinate(i, j);
        }
      }
    }
    return Coordinate.INVALID_COORDINATE;
  }

  /**
   * unchoose all the block at once
   */

  public void unChooseAllBlocks() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).isChosen()) {
          allBlocks.getBlock(new Coordinate(i, j)).unchoose();
        }
      }
    }
  }

  /**
   * cross a piece
   * @param pieceToRemoveCoordinate the coordinate of the piece to cross
   */

  private void crossPiece(Coordinate pieceToRemoveCoordinate) {
    allBlocks.getBlock(pieceToRemoveCoordinate).setEmpty();
  }

  /**
   * make a block to be a king piece
   * @param newCoordinate the coordinate of the piece
   */

  public void makeBlockKing(Coordinate newCoordinate) {
    if (((newCoordinate.yCoordinate() == 0
        && allBlocks.getBlock(newCoordinate).getPlayerID() == 2) ||
            (newCoordinate.yCoordinate() == allBlocks.getBlockStructureHeight() - 1
            && allBlocks.getBlock(newCoordinate).getPlayerID() == 1))) {
      CheckersBlock newKing = new CheckersBlock(allBlocks.getBlock(newCoordinate));
      if(!newKing.isKing()) {
        newKing.makeKing();
        allBlocks.getBlock(newCoordinate).initiateBlockState(newKing.getState());
      }
    }
  }

  /**
   * check whether last move is a winning move for checkers game
   * @param playerID the current index of the player
   * @return whether last move is a winning move
   */


  public boolean isWinningMove(int playerID) {
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++) {
        Coordinate coordinate = new Coordinate(j, i);
        if (allBlocks.getBlock(coordinate).getPlayerID() == playerTakeTurn(playerID,
            CheckersGame.PLAYER_INDEX_POLL) && !allBlocks.getBlock(coordinate)
            .getAvailablePositions(playerTakeTurn(playerID, CheckersGame.PLAYER_INDEX_POLL),
                allBlocks).isEmpty()) {
          return false;
        }
      }
    }
    return true;
  }
}
