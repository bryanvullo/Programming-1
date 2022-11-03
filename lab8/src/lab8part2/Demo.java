package lab8part2;

import java.util.ArrayList;
import java.util.Collections;

public class Demo {
  static ArrayList<Animal> animals = new ArrayList<Animal>();
  public static void main(String[] args) {
    Wolf wolf1 = new Wolf("Wolfy", 8);
    Wolf wolf2 = new Wolf("Watson", 17);
    Parrot parrot1 = new Parrot("Penny", 3);
    Parrot parrot2 = new Parrot("Pritchet", 19);
    Rabbit rabbit1 = new Rabbit("Peter", 6);
    Rabbit rabbit2 = new Rabbit("Rogers", 9);

    animals.add(wolf1);
    animals.add(wolf2);
    animals.add(parrot1);
    animals.add(parrot2);
    animals.add(rabbit1);
    animals.add(rabbit2);

    // before sorting
    for (Animal animal : animals) {
      System.out.println(animal.name + ": " + animal.age);
    }
    // sorting
    Collections.sort(animals);
    // after soring
    for (Animal animal : animals) {
      System.out.println(animal.name + ": " + animal.age);
    }
    // sorts animals from youngest to oldest,
    // to reverse the order, make the compareTo method return the opposite values (1 instead of -1 and vice versa)
  }

  /*
  INTERFACES
  an interface is a completely abstract class in which it groups togethere related methods with no bodies,
  the bodies are specified in another class.
  Interfaces must be 'implemented' rather than 'inheritied'
   */
}
