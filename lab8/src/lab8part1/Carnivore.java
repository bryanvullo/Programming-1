package lab8part1;

abstract class Carnivore extends Animal {
  public Carnivore(String name, int age) {
    super(name, age);
  }

  public Carnivore() {
    super();
  }

  public void eat(Food food) throws Exception {
    if (food instanceof Meat) {
      System.out.println(this.getName() + " is eating " + food.getName());
    }
    else {
      throw new Exception("Carnivores can only eat Meat");
    }
  }

  public void eat(Food food, Integer numberOfTimes) throws Exception {
    if (!(food instanceof Meat)) {
      throw new Exception("Carnivores can only eat Meat");
    }
    else {
      super.eat(food, numberOfTimes);
    }
  }
}
