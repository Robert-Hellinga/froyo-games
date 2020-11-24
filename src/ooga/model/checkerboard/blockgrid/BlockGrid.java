package ooga.model.checkerboard.blockgrid;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.Util;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.exceptions.FileException;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;

public abstract class BlockGrid {

  protected final int numPlayers;
  protected final BlockStructure allBlocks;
  protected String gameType;
  protected boolean finishARound;

  public BlockGrid(BlockGrid originalGrid) {
    finishARound = false;
    this.gameType = originalGrid.gameType;
    this.allBlocks = new BlockStructure(this.gameType,
        originalGrid.allBlocks.getBlockConfigStructure());
    this.numPlayers = originalGrid.numPlayers;
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
        this.getAllBlocks().getBlock(new Coordinate(i, j)).initiateBlockState(
            originalGrid.allBlocks.getBlock(new Coordinate(i, j)).getState());
      }
    }
  }

  public BlockGrid(String gameType, List<List<Integer>> allBlockConfig, int numPlayers) {
    this.gameType = gameType;
    this.allBlocks = new BlockStructure(this.gameType, allBlockConfig);
    this.numPlayers = numPlayers;
  }

  /**
   * set all the block state
   * @param stateString new block state
   */

  public void setAllBlockStates(String stateString) {
    List<List<Block>> newBlockStates = new ArrayList<>();
    String[] rows = stateString.split("~");

    for (int i = 0; i < rows.length; i++) {
      String[] row = rows[i].split(",");
      List<Block> blockLine = new ArrayList<>();
      for (int j = 0; j < row.length; j++) {
        int cellConfig = Integer.parseInt(row[j]);
        if (cellConfig != -1) {
          blockLine.add(BlockGrid.createBlock(gameType, cellConfig, new Coordinate(j, i)));
        }
      }
      newBlockStates.add(blockLine);
    }
    allBlocks.setBlockStructure(newBlockStates);
  }

  /**
   * get all the block states
   * @return all the block states
   */

  public List<List<Integer>> getAllBlockStates() {
    List<List<Integer>> blockState = new ArrayList<>();
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++) {
      List<Integer> blockStateLine = new ArrayList<>();
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++) {
        Block currentBlock = allBlocks.getBlock(new Coordinate(j, i));
        blockStateLine.add(currentBlock.getState());
      }
      blockState.add(blockStateLine);
    }
    return blockState;
  }

  /**
   * get all the block as String
   * @return all the block state as String
   */

  public String getAllBlockStatesAsString() {
    BlockStructure blocks = getAllBlocks();
    String result = "";
    for (int i = 0; i < blocks.getBlockStructureHeight(); i++) {
      for (int j = 0; j < blocks.getBlockStructureWidth(); j++) {
        Block currentBlock = blocks.getBlock(new Coordinate(j, i));
        result += currentBlock.getState() + ",";
      }
      result += "~";
    }
    return result;
  }

  /**
   * create a block
   * @param gameType given type of the game
   * @param blockConfig block configuration
   * @param coordinate the coordinate of the block
   * @return the block
   * @throws ClassOrMethodNotFoundException
   */

  public static Block createBlock(String gameType, int blockConfig, Coordinate coordinate)
      throws ClassOrMethodNotFoundException {
    return Util.reflect(
        "ooga.model.checkerboard.block." + gameType + "Block",
        List.of(Integer.class, Coordinate.class),
        List.of(blockConfig, coordinate)
    );
  }

  /**
   * get all the blocks
   * @return all the blocks
   */

  public BlockStructure getAllBlocks() {
    return allBlocks;
  }


  /**
   * set all the available position for the next round
   * @param currentPlayerIndex the index of the current player
   * @param chosenBlock the coordinate of the chosen block
   */
  public abstract void setAvailablePositions(int currentPlayerIndex, Coordinate chosenBlock);

  public void unsetAllBlockPotentials() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).isPotentialMove()) {
          allBlocks.getBlock(new Coordinate(i, j)).unmakePotentialMove();
        }
      }
    }
  }

  /**
   * check if there is a chosen block on the grid
   * @return whether there is a chosen block on the grid
   */

  public boolean hasChosenBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).isChosen()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * move the piece to a new position
   * @param originalCoordinate the original coordinate of the piece
   * @param newCoordinate the new coordinate of the piece
   */

  public void moveBlock(Coordinate originalCoordinate, Coordinate newCoordinate) {
    allBlocks.getBlock(newCoordinate).initiateBlockState
        (allBlocks.getBlock(originalCoordinate).getState());
    allBlocks.getBlock(originalCoordinate).setEmpty();
  }

  /**
   * check whether a round is finished
   * @return whether a round is finished
   */

  public boolean isFinishARound() {
    return finishARound;
  }

  /**
   * reset the round finishing checker
   */

  public void resetFinishARound() {
    this.finishARound = false;
  }

  /**
   * play the game according to the given coordinate
   * @param passInCoordinate the given coordinate
   * @param currentPlayerIndex the index of the current player
   */

  public abstract void play(Coordinate passInCoordinate, Integer currentPlayerIndex);

  /**
   * check whether the last move is a winning move
   * @param playerID the current index of the player
   * @return whether the move is a winning move
   */
  public abstract boolean isWinningMove(int playerID);

  public static int playerTakeTurn(Integer currentPlayerIndex, List<Integer> playerIndexPoll) {
    int index = playerIndexPoll.indexOf(currentPlayerIndex);
    if (index == 0) {
      return 1;
    } else if (index == 1) {
      return 2;
    } else {
      throw new FileException("Invalid player");
    }
  }

  /**
   * get all the potential moves that the player can play
   * @param currentPlayerIndex the index of the current player
   * @return the coordinates of all the moves that the player can play
   */

  public List<Coordinate> getAllPotentialMoves(int currentPlayerIndex) {
    List<Coordinate> allPotentialMoves = new ArrayList<>();
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
        Coordinate coordinate = new Coordinate(i, j);
        allPotentialMoves.addAll(allBlocks.getBlock(coordinate)
            .getAvailablePositions(currentPlayerIndex, allBlocks));
      }
    }
    return allPotentialMoves;
  }
}
