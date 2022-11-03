package lab8part3;

public class JetPlane extends Refuelable {
  public void location() {
    System.out.println("this vehicle can only operate in air and lands on land");
  }

  public void load() {
    //load passengers and cargo into plane
  }

  public void move() {
    System.out.println("the plane is in the air");
  }
}
