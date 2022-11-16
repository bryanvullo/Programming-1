package lab8part3;

abstract class Cycle extends RoadVehicle {
  //cycles are a type of road vehicle
  public void load() {
    System.out.println("sit on the cycle");
  }

  public void move() {
    System.out.println("peddal to move!");
  }

  public void location() {
    super.location();
  }

  abstract int getWheels();
}
