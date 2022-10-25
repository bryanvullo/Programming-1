package lab6part3;

public class Rabbit extends Herbivore {
  public Rabbit(String name, int age) {
    super(name, age);
  }

  public void makeNoise() {
    System.out.println("Sqeakkkk!");
  }
}
