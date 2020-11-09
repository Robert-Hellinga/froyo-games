package ooga.model.player;

public class Player{

  public enum PlayerType{
    AI,
    HUMAN
  }

  private String name;
  private int ID;
  private PlayerType type;

  public Player(String name, PlayerType playerType){
    this.name = name;
    this.type = playerType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getID() {
    return ID;
  }

  public PlayerType getType() {
    return type;
  }


  public void setID(int ID) {
    this.ID = ID;
  }

  public void setType(PlayerType type) {
    this.type = type;
  }

}
