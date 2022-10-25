package lab6part3;

abstract class Carnivore extends Animal {
  public Carnivore(String name, int age) {
    super(name, age);
  }

  public void eat(Food food) throws Exception {
    if (food instanceof Meat) {
      System.out.println(this.getName() + " is eating " + food.getName());
    }
    else {
      throw new Exception("Carnivores can only eat Meat");
    }
  }
}
