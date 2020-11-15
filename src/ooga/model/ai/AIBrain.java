package ooga.model.ai;

import java.util.List;
import ooga.Coordinate;
import ooga.model.BlockGrid;

public interface AIBrain {

  List<Coordinate> decideMove(BlockGrid checkBoard, Integer currentPlayerIndex);
}
