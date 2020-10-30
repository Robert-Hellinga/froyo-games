package ooga.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javafx.scene.Group;
import ooga.Exception.ClassOrMethodNotFoundException;
import ooga.View.Pieces.Piece;

public class PieceGrid {
  private PieceStructure allPieces;
  private String gameType;
  private Group root;

  public PieceGrid(String gameType, PieceStateStructure initiationPiecesState, Group root){
    this.gameType = gameType;
    allPieces = new PieceStructure(this.gameType, initiationPiecesState);
    this.root = root;
    setAllPiecesToRoot();
  }

  public static Piece createPiece(String gameType, int state, int xCoordinate, int yCoordinate){
    try{
      Class<?> piece = Class.forName("ooga.View.Pieces." + gameType + "Piece");
      Class<?>[] param = {Integer.class, Integer.class, Integer.class};
      Constructor<?> cons = piece.getConstructor(param);
      Object[] paramObject = {state, xCoordinate, yCoordinate};
      Object gamePiece = cons.newInstance(paramObject);
      return (Piece) gamePiece;
    }
    catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e){
      throw new ClassOrMethodNotFoundException("class or method is not found");
    }
  }

  private void setAllPiecesToRoot(){
    for (List<Piece> piecesLine : allPieces.getAllPieces()){
      for (Piece piece : piecesLine){
        root.getChildren().add(piece.getPieceShape());
      }
    }
  }
}
