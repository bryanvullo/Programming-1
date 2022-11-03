package lab8part1;

abstract class Animal {
  String name;
  int age;

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Animal() {
    this("newborn", 0);
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public abstract void makeNoise();

  public abstract void eat(Food food) throws Exception;

  public void eat(Food food, Integer numberOfTimes) throws Exception {
    for (int i = 0; i < numberOfTimes; i++) {
      System.out.println(getName() + " is eating " + food.getName());
    }
  }
}
