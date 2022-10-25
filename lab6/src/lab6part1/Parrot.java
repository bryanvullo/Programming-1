package lab6part1;

public class Parrot extends Animal {
  public Parrot(String name, int age) {
    super(name, age);
  }

  public void makeNoise() {
    System.out.println("my name is " + getName());
  }
}
