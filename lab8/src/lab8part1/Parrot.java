package lab8part1;

public class Parrot extends Omnivore {
  public Parrot(String name, int age) {
    super(name, age);
  }

  public Parrot(int age) {
    this("Polly", age);
  }

  public void makeNoise() {
    System.out.println("Squarkkkkk!");
  }
}
