package lab8part3;

abstract class RoadVehicle extends Transport {
  //extends transport as these are vehicles that only operate on roads
  public void location() {
    System.out.println("this vehicle can only operate on the road");
  }

  abstract int getWheels();
}
