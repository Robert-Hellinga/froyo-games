package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;

public class Connect4Game extends Game {

  private Connect4BlockGrid checkBoard;

  public Connect4Game(String gameType, String playerName, PlayerMode playerMode) {
    super(gameType, playerName, playerMode);
    checkBoard = new Connect4BlockGrid(gameType, getInitiationBlockConfig(gameType), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    int xCoordinate = passInCoordinate.xCoordinate();
    for (int i = 0; i < checkBoard.getAllBlocks().getBlockStructureHeight(); i++) {
      Coordinate coordinate = new Coordinate(xCoordinate,
          checkBoard.getAllBlocks().getBlockStructureHeight() - 1 - i);
      if (checkBoard.getAllBlocks().getBlock(coordinate).getBlockState().isPotentialMove()) {
        checkBoard.addBlock(getCurrentPlayerIndex(), coordinate);
      }
    }
  }

  @Override
  public BlockGrid getCheckBoard() {
    return checkBoard;
  }
}
