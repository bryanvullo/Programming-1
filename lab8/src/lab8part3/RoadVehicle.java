package lab8part3;

abstract class RoadVehicle extends Refuelable{
  int numberOfWheels;

  public void location() {
    System.out.println("this vehicle can only operate on the road");
  }
}
