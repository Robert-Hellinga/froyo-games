package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.BlockGrid;
import ooga.model.checkerboard.CheckersBlockGrid;

public class CheckersGame extends Game {

  private CheckersBlockGrid checkBoard;

  public CheckersGame(String gameType, String playerName, PlayerMode playerMode){
    super(gameType, playerName, playerMode);
    checkBoard = new CheckersBlockGrid(gameType, getInitiationBlockConfig(gameType), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    if (checkBoard.hasChosenBlock()) {
      if (checkBoard.getAllBlocks().getBlock(passInCoordinate).getPlayerID()
          == getCurrentPlayerIndex()) {
        checkBoard.unChoseAllBlock();
        checkBoard.unsetAllBlockPotential();
        checkBoard.getAllBlocks().getBlock(passInCoordinate).getBlockState().choose();
        checkBoard.setAvailablePosition(getCurrentPlayerIndex(), passInCoordinate);
      } else if (checkBoard.getAllBlocks().getBlock(passInCoordinate).getBlockState()
          .isPotentialMove()) {

        checkBoard.removeCheckedPiece(passInCoordinate, checkBoard.getChosenBlockCoordianate());
        checkBoard.moveBlock(checkBoard.getChosenBlockCoordianate(), passInCoordinate);
        checkBoard.makeBlockKing(passInCoordinate);
        checkBoard.unChoseAllBlock();
        checkBoard.unsetAllBlockPotential();
        checkBoard.getAllBlocks().getBlock(passInCoordinate).setPlayerID(getCurrentPlayerIndex());
        playerTakeTurn();
      }
    } else {
      if (!checkBoard.getAllBlocks().getBlock(passInCoordinate).getIsEmpty()
          && checkBoard.getAllBlocks().getBlock(passInCoordinate).getPlayerID()
          == getCurrentPlayerIndex()) {
        checkBoard.getAllBlocks().getBlock(passInCoordinate).getBlockState().choose();
        checkBoard.setAvailablePosition(getCurrentPlayerIndex(), passInCoordinate);
      }
    }
    notifyObservers();
  }

  @Override
  public BlockGrid getCheckBoard(){
    return checkBoard;
  }
}
