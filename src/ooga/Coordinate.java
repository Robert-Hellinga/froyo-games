package ooga;

public class Coordinate {

  public static final Coordinate INVALID_COORDINATE = new Coordinate(-1, -1);

  int xCoordinate;
  int yCoordinate;

  public Coordinate(int xCoordinate, int yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public int xCoordinate() {
    return xCoordinate;
  }

  public int yCoordinate() {
    return yCoordinate;
  }

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

  @Override
  public String toString() {
    return "Coordinate{" +
        "xCoordinate=" + xCoordinate +
        ", yCoordinate=" + yCoordinate +
        '}';
  }
}
