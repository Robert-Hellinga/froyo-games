package ooga.Model.Player;

public abstract class Player {

  private static String name;
  private static int ID;

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
