package ooga.model.checkerboard.blockgrid;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.Util;
import ooga.exceptions.ClassOrMethodNotFoundException;
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
        this.getAllBlocks().getBlock(new Coordinate(i, j)).initiateBlockState( //TODO: Double check if this breaks anything
            originalGrid.allBlocks.getBlock(new Coordinate(i, j)).getState());
      }
    }
  }

  public BlockGrid(String gameType, List<List<Integer>> allBlockConfig, int numPlayers) {
    this.gameType = gameType;
    this.allBlocks = new BlockStructure(this.gameType, allBlockConfig);
    this.numPlayers = numPlayers;
  }

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

  public static Block createBlock(String gameType, int blockConfig, Coordinate coordinate)
      throws ClassOrMethodNotFoundException {
    return Util.reflect(
        "ooga.model.checkerboard.block." + gameType + "Block",
        List.of(Integer.class, Coordinate.class),
        List.of(blockConfig, coordinate)
    );
  }

  public BlockStructure getAllBlocks() {
    return allBlocks;
  }

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

  public void moveBlock(Coordinate originalCoordinate, Coordinate newCoordinate) {
    allBlocks.getBlock(newCoordinate).initiateBlockState
            (allBlocks.getBlock(originalCoordinate).getState());
    allBlocks.getBlock(originalCoordinate).initiateBlockState(0);
  }

  public boolean isFinishARound() {
    return finishARound;
  }

  public void resetFinishARound() {
    this.finishARound = false;
  }

  public abstract void play(Coordinate passInCoordinate, Integer currentPlayerIndex);

  public abstract boolean isWinningMove(int playerID);

  public static int playerTakeTurn(Integer currentPlayerIndex, List<Integer> playerIndexPoll) {
    int index = playerIndexPoll.indexOf(currentPlayerIndex);
    return playerIndexPoll.get((index + 1) % 2);
  }

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
