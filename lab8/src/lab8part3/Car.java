package lab8part3;

public class Car extends RoadVehicle implements Refuelable{
  int numberOfWheels = 4;
  boolean locked = true;

  public void unlock() {
    locked = false;
  }

  public void lock() {
    locked = true;
  }

  public void move() {
    //takes in a destination
    //moves the car to destination
  }

  public void load() {
    //takes in passengers as parameters
    //load passengers into car
  }

  public void refuel() {
    System.out.println("The car has been refueled");
  }

  public int getWheels() {
    return 4;
  }
}
