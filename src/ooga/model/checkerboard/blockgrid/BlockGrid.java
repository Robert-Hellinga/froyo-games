package ooga.model.checkerboard.blockgrid;

import java.util.ArrayList;
import java.util.List;
import ooga.Coordinate;
import ooga.Util;
import ooga.exceptions.ClassOrMethodNotFoundException;
import ooga.model.checkerboard.BlockConfigStructure;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
import ooga.model.checkerboard.block.BlockState;

public abstract class BlockGrid {

  protected final int numPlayers;
  protected final BlockStructure allBlocks;
  protected String gameType;
  protected boolean finishARound = false;

  public BlockGrid(BlockGrid originalGrid) {
    this.gameType = originalGrid.gameType;
    this.allBlocks = new BlockStructure(this.gameType,
        originalGrid.allBlocks.getBlockConfigStructure());
    this.numPlayers = originalGrid.numPlayers;
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
        this.getAllBlocks().getBlock(new Coordinate(i, j)).setBlockState(
            new BlockState(originalGrid.allBlocks.getBlock(new Coordinate(i, j)).getBlockState()));
      }
    }
  }

  public BlockGrid(String gameType, BlockConfigStructure allBlockConfig, int numPlayers) {
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

  public void setChosenBlock(Coordinate chosenBlock) {
    allBlocks.getBlock(chosenBlock).getBlockState().choose();
  }

  public abstract void setAvailablePosition(int currentPlayerIndex, Coordinate chosenBlock);


  public boolean hasChosenBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isChosen()) {
          return true;
        }
      }
    }
    return false;
  }

  public Coordinate getChosenBlockCoordianate() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isChosen()) {
          return new Coordinate(i, j);
        }
      }
    }
    return Coordinate.INVALID_COORDINATE;
  }

  public void unChoseAllBlock() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isChosen()) {
          allBlocks.getBlock(new Coordinate(i, j)).getBlockState().unchoose();
        }
      }
    }
  }

  public void unsetAllBlockPotential() {
    for (int j = 0; j < allBlocks.getBlockStructureHeight(); j++) {
      for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
        if (allBlocks.getBlock(new Coordinate(i, j)).getBlockState().isPotentialMove()) {
          allBlocks.getBlock(new Coordinate(i, j)).getBlockState().unmakePotentialMove();
        }
      }
    }
  }

  public void moveBlock(Coordinate originalCoordiante, Coordinate newCoordinate) {
    allBlocks.getBlock(newCoordinate).setBlockState(
        new BlockState(allBlocks.getBlock(originalCoordiante).getBlockState())
    );
    allBlocks.getBlock(newCoordinate).getBlockState().setChosen(false);
    allBlocks.getBlock(originalCoordiante).initiateBlockState(0);
  }

  public boolean isFinishARound() {
    return finishARound;
  }

  public void resetFinishAround() {
    this.finishARound = false;
  }

  public abstract void play(Coordinate passInCoordinate, Integer currentPlayerIndex);

  public abstract boolean isWinningMove(int playerID);

  public static int playerTakeTurn(Integer currentPlayerIndex, List<Integer> playerIndexPoll) {
    int index = playerIndexPoll.indexOf(currentPlayerIndex);
    if (index == playerIndexPoll.size() - 1) {
      return playerIndexPoll.get(0);
    } else {
      return playerIndexPoll.get(index + 1);
    }
  }

  public List<Coordinate> getAllPotentialMoves(int currentPlayerIndex) {
    List<Coordinate> allPotentialMoves = new ArrayList<>();
    for (int i = 0; i < allBlocks.getBlockStructureWidth(); i++) {
      for (int j = allBlocks.getBlockStructureHeight() - 1; j >= 0; j--) {
        Coordinate coordinate = new Coordinate(i, j);
        allPotentialMoves.addAll(allBlocks.getBlock(coordinate)
            .getAvailablePosition(currentPlayerIndex, allBlocks));
      }
    }
    return allPotentialMoves;
  }
}
