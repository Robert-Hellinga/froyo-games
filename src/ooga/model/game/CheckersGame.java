package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;

public class CheckersGame extends Game {

  public CheckersGame(String gameType, String playerName, PlayerMode playerMode){
    super(gameType, playerName, playerMode);
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
}
