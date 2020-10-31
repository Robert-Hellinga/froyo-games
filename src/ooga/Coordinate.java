package ooga;

public record Coordinate(int xCoordinate, int yCoordinate) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordinate that = (Coordinate) o;
    return xCoordinate == that.xCoordinate &&
        yCoordinate == that.yCoordinate;
  }
}
