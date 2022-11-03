package lab8part3;

public class Car extends RoadVehicle{
  int numberOfWheels = 4;
  boolean locked = true;

  public void unlock() {
    locked = false;
  }

  public void lock() {
    locked = true;
  }

  public void move() {
    System.out.println("start engine and press the accelerator to move");
  }

  public void load() {
    //load passengers into car
  }
}
