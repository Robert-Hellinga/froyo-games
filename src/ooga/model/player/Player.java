package ooga.model.player;

public abstract class Player {

  protected static String name;
  protected static int ID;

  public Player(String name) {
    this.name = name;
  }

  public static int getID() {
    return ID;
  }

  public static String getName() {
    return name;
  }

  public static void setID(int ID) {
    Player.ID = ID;
  }

  public static void setName(String name) {
    Player.name = name;
  }
}
