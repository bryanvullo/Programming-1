package lab8part3;

public class Cycle implements Transport{
  public void load() {
    System.out.println("sit on the cycle");
  }

  public void move() {
    System.out.println("peddal to move!");
  }

  public void location() {
    System.out.println("can only operate in roads");
  }
}
