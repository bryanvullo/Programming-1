package lab8part3;

public class Train extends Transport implements Refuelable {
  public void location() {
    System.out.println("this vehicle can only operate on train tracks");
  }

  public String getCurrentLocation() {
    //returns the current location
    return "Current destination";
  }

  public void stop() {
    //stops the train
  }
  public void move() {
    //takes in a destination as a parameter
    String destination = "destination";
    System.out.println("CHOOO CHOOO!");
    if (getCurrentLocation() == destination) {
      stop();
    }
  }

  public void load() {
    //takes in passengers and cargo as parameters
    //load passengers and cargo onto train
  }

  public void refuel() {
    System.out.println("the train has been refueled");
  }
}
