package ooga.model.game;

import ooga.Coordinate;
import ooga.controller.GameController.PlayerMode;
import ooga.model.checkerboard.blockgrid.BlockGrid;
import ooga.model.checkerboard.blockgrid.Connect4BlockGrid;

public class Connect4Game extends Game {

  private Connect4BlockGrid connect4Board;

  public Connect4Game(String gameType, String playerName, PlayerMode playerMode) {
    super(gameType, playerName, playerMode);
    connect4Board = new Connect4BlockGrid(gameType, getInitiationBlockConfig(gameType), numPlayers);
  }

  @Override
  public void play(Coordinate passInCoordinate) {
    int xCoordinate = passInCoordinate.xCoordinate();
    for (int i = 0; i < connect4Board.getAllBlocks().getBlockStructureHeight(); i++) {
      Coordinate coordinate = new Coordinate(xCoordinate,
          connect4Board.getAllBlocks().getBlockStructureHeight() - 1 - i);
      if (connect4Board.getAllBlocks().getBlock(coordinate).getBlockState().isPotentialMove()) {
        connect4Board.addBlock(getCurrentPlayerIndex(), coordinate);
      }
    }
  }

  @Override
  public BlockGrid getBoard() {
    return connect4Board;
  }
}
