package lab6part3;

abstract class Animal {
  String name;
  int age;

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public abstract void makeNoise();

  public abstract void eat(Food food) throws Exception;
}
