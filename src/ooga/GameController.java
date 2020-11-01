package ooga;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import ooga.model.Game;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
import ooga.view.piecegrid.CheckersPieceGrid;
import ooga.view.piecegrid.PieceGrid;
import ooga.view.PieceStateStructure;
import ooga.view.pieces.Piece;

public class GameController {

  public enum PlayerMode {
    PLAY_WITH_AI,
    PLAY_WITH_FRIEND
  }


  private PieceGrid pieceGrid;
  private Game game;
  private int playerInTurn;
  private Coordinate pieceChosen;


  public GameController(String gameType, Group root) {
    game = new Game(gameType, "Anna", ooga.controller.GameController.PlayerMode.PLAY_WITH_AI);
    pieceGrid = new CheckersPieceGrid(gameType, convertBlockToPiece(game.getCheckBoard().getAllBlocks()), root);
  }

  public void update() {
    playerInTurn = game.getCurrentPlayerIndex();
    pieceGrid.updatePieceCenBeChosenOrNotStatus(playerInTurn);
    pieceChosen = pieceGrid.getChosenPieceCoordinate();
    game.updatePieceChosen(pieceChosen);
    pieceGrid.showPotentialMoveAndMakeClickable(game.getPotentialMovePos());
    pieceGrid.updateNonemptyColor();
    convertPieceStateToBlock(game.getCheckBoard().getAllBlocks(), pieceGrid.getCurrentPieceState());

  }

  public PieceStateStructure convertBlockToPiece(BlockStructure allBlocks){
    List<List<Integer>> pieceStateStructure = new ArrayList<>();
    for (List<Block> blockList : allBlocks.getBlockStructure()){
      List<Integer> pieceStateLine = new ArrayList<>();
      for (Block block : blockList){
        if (block.getIsEmpty()){
          pieceStateLine.add(Piece.EMPTY_STATE);
        }
        else{
          pieceStateLine.add(block.getPlayerID());
        }
      }
      pieceStateStructure.add(pieceStateLine);
    }
    return new PieceStateStructure(pieceStateStructure);
  }

  public void convertPieceStateToBlock(BlockStructure allBlocks, PieceStateStructure pieceStateStructure){
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++){
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++){
        Coordinate pieceCoordinate = new Coordinate(j, i);
        if (pieceStateStructure.getPieceState(pieceCoordinate) == Piece.EMPTY_STATE){
          allBlocks.getBlock(pieceCoordinate).setEmpty(true);
        }
        allBlocks.getBlock(pieceCoordinate).setPlayerID(pieceStateStructure.getPieceState(pieceCoordinate));
      }
    }
  }
}
