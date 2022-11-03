package lab8part3;

abstract class Refuelable implements Transport {
  public void refuel() {
    System.out.println("the vehicle is now refueled");
  }

  public abstract void load();

  public abstract void move();

  public abstract void location();
}
