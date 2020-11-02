package ooga;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import ooga.model.Game;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.Block;
import ooga.view.newversion.PieceGrid;
import ooga.view.newversion.PieceStateStructure;
import ooga.view.newversion.Piece;

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
    pieceGrid = new PieceGrid(convertBlockToPiece(game.getCheckBoard().getAllBlocks()), root);
  }

//  public void update() {
//    playerInTurn = game.getCurrentPlayerIndex();
//    pieceGrid.updatePieceCenBeChosenOrNotStatus(playerInTurn);
//    pieceChosen = pieceGrid.getChosenPieceCoordinate();
//    game.updatePieceChosen(pieceChosen);
//    pieceGrid.showPotentialMoveAndMakeClickable(game.getPotentialMovePos());
//    if (pieceGrid.checkIfPieceIsMoved()){
//      convertPieceStateToBlock(game.getCheckBoard().getAllBlocks(), pieceGrid.getCurrentPieceState());
//      game.removeCheckedPiece(pieceGrid.getNewlyMovedPosition(), pieceChosen);
//      pieceGrid.setPieceState(convertBlockToPiece(game.getCheckBoard().getAllBlocks()));
//      game.playerTakeTurn();
//      pieceGrid.updateAllColor();
//      pieceGrid.resetPieceChosenAndMovedChecker();
//    }
//  }

  public void update() {
    //TODO: implement new version update method
    if (pieceGrid.checkIfPieceIfClicked()){
      //game.play(pieceGrid.getClickedPieceCoordinate());
      //pieceGrid.updatePieceState(convertBlockStateToPiece(game.getCheckBoard().getBlockState()));
      pieceGrid.resetPiecesClickedStatus();
    }

  }

  public PieceStateStructure convertBlockToPiece(BlockStructure allBlocks) {
    List<List<Integer>> pieceStateStructure = new ArrayList<>();
    for (List<Block> blockList : allBlocks.getBlockStructure()) {
      List<Integer> pieceStateLine = new ArrayList<>();
      for (Block block : blockList) {
        if (block.getIsEmpty()) {
          pieceStateLine.add(Piece.EMPTY_STATE);
        } else {
          pieceStateLine.add(block.getPlayerID());
        }
      }
      pieceStateStructure.add(pieceStateLine);
    }
    return new PieceStateStructure(pieceStateStructure);
  }

  public void convertPieceStateToBlock(BlockStructure allBlocks,
      PieceStateStructure pieceStateStructure) {
    for (int i = 0; i < allBlocks.getBlockStructureHeight(); i++) {
      for (int j = 0; j < allBlocks.getBlockStructureWidth(); j++) {
        Coordinate pieceCoordinate = new Coordinate(j, i);
        if (pieceStateStructure.getPieceState(pieceCoordinate) == Piece.EMPTY_STATE) {
          allBlocks.getBlock(pieceCoordinate).setEmpty(true);
        } else {
          allBlocks.getBlock(pieceCoordinate).setEmpty(false);
        }
        allBlocks.getBlock(pieceCoordinate)
            .setPlayerID(pieceStateStructure.getPieceState(pieceCoordinate));
      }
    }
  }
}
