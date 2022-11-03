package lab8part3;

public class Train extends Refuelable {
  public void location() {
    System.out.println("this vehicle can only operate on train tracks");
  }

  public void move() {
    System.out.println("CHOOO CHOOO!");
  }

  public void load() {
    //load passengers and cargo onto train
  }
}
