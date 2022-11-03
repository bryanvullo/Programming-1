package lab8part2;

public class Rabbit extends Herbivore {
  public Rabbit(String name, int age) {
    super(name, age);
  }

  public void makeNoise() {
    System.out.println("Sqeakkkk!");
  }
}
