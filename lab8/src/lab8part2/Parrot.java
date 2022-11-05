<<<<<<<< HEAD:lab8/src/lab8part2/Parrot.java
package lab8part2;
========
package lab8part1;
>>>>>>>> origin/master:lab8/src/lab8part1/Parrot.java

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
