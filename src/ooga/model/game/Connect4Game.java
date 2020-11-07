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
    connect4Board.setAvailablePosition(getCurrentPlayerIndex(), passInCoordinate);
    for (int j = connect4Board.getAllBlocks().getBlockStructureHeight() - 1; j >= 0; j--) {
      Coordinate coordinate = new Coordinate(xCoordinate, j);
      if (connect4Board.getAllBlocks().getBlock(coordinate).getBlockState().isEmpty()) {
//        connect4Board.addBlock(getCurrentPlayerIndex(), coordinate);
        connect4Board.getAllBlocks().getBlock(coordinate).setPlayerID(getCurrentPlayerIndex());
        playerTakeTurn();
        break;
      }
    }
  }

  @Override
  public BlockGrid getBoard() {
    return connect4Board;
  }
}
