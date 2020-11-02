package ooga.view.grid;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import ooga.Coordinate;
import ooga.controller.GameController;
import ooga.model.checkerboard.BlockStructure;
import ooga.model.checkerboard.block.BlockState;

public class PieceGrid extends GridPane {

  private PieceStructure allPieces;
  private GameController controller;

  public PieceGrid(GameController controller) {
    PieceStateStructure initiationPiecesState =
        convertBlockStateStrucToPieceStateStruc(controller.getAllBlocks());

    System.out.println(initiationPiecesState);

    allPieces = new PieceStructure(initiationPiecesState, controller);
    System.out.println(allPieces);
    setAllPiecesToGrid();
  }

  private void setAllPiecesToGrid() {
    for(int i = 0; i < allPieces.getPieceStructureHeight(); i++){
      for(int j = 0; j < allPieces.getPieceStructureWidth(); j++){
        Coordinate coordinate = new Coordinate(j, i);
        add(allPieces.getPiece(coordinate).getPieceShape(), j, i);
      }
    }
  }

  public PieceStructure getAllPieces() {
    return allPieces;
  }

//  public void updatePieceState(){
//    PieceStateStructure allPiecesState =
//        convertBlockStateStrucToPieceStateStruc(controller.getAllBlocks());
//    for(int i = 0; i < allPieces.getPieceStructureHeight(); i++){
//      for(int j = 0; j < allPieces.getPieceStructureWidth(); j++){
//        Coordinate coordinate = new Coordinate(j, i);
//        allPieces.getPiece(coordinate).setState(allPiecesState.getPieceState(coordinate));
//        allPieces.getPiece(coordinate).updateColor();
//      }
//    }
//  }

//  private PieceStateStructure convertBlockStateStrucToPieceStateStruc(BlockStructure allBlocks){
//    List<List<Integer>> pieceState = new ArrayList<>();
//    for(int i = 0; i < allBlocks.getBlockStructureHeight(); i++){
//      List<Integer> pieceStateList = new ArrayList<>();
//      for(int j = 0; j < allBlocks.getBlockStructureWidth(); j++){
//        pieceStateList.add(convertBlockStateToBlockState(allBlocks.getBlock(new Coordinate(j,i)).getBlockState()));
//      }
//      pieceState.add(pieceStateList);
//    }
//    return new PieceStateStructure(pieceState);
//  }

  private Integer convertBlockStateToBlockState(BlockState blockState){
    if (blockState.isEmpty() && !blockState.isPotentialMove()){
      return 0;
    }
    else if (blockState.isPotentialMove()){
      return 5;
    }
    else {
      if (!blockState.isChosen()) {
        return blockState.getPlayerID();
      }
      else{
        return blockState.getPlayerID() + 2;
      }
    }
  }

}
