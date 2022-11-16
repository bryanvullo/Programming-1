package lab8part3;

public class JetPlane extends Transport implements Refuelable {
  String location;
  public void takeOff() {
    //transitions from land to air
    location = "air";
  }
  public void land() {
    //transitions from air to land
    location = "land";
  }
  public void location() {
    System.out.println("this vehicle can only operate in air and lands on land");
  }

  public void load() {
    //takes in cargo and passengers as parameters
    //load passengers and cargo into plane
  }

  public void move() {
    //takes in a destination
    System.out.println("the plane is in the air");
    //lands at destination
  }

  public void refuel() {
    System.out.println("the jet plane has been refueled");
  }
}
