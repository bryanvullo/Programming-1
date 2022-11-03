package lab8part1;

abstract class Herbivore extends Animal {
  public Herbivore(String name, int age) {
    super(name, age);
  }

  public void eat(Food food) throws Exception {
    if (food instanceof Plant) {
      System.out.println(this.getName() + " is eating " + food.getName());
    }
    else {
      throw new Exception("Herbivores can only eat Plant");
    }
  }
}
