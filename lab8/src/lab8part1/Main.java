package lab8part1;

public class Main {
  public static void main(String[] args) {
    Parrot myParrot = new Parrot("Peter", 3);
    Wolf myWolf = new Wolf("Watson", 12);
    Rabbit myRabbit = new Rabbit("Robert", 7);

    System.out.println(myParrot.getName() + ": " + myParrot.getAge());
    System.out.println(myWolf.getName() + ": " + myWolf.getAge());

    myParrot.makeNoise();
    myWolf.makeNoise();

    Plant myPlant = new Plant("Spinach");
    Meat myMeat = new Meat("Beef");

    System.out.println(myPlant.getName());
    System.out.println(myMeat.getName());

    try {
      myWolf.eat(myMeat);
      myRabbit.eat(myPlant);
      myWolf.eat(myMeat, 3);
    }
    catch (Exception e) {
      System.err.println(e);
    }

    Parrot myNewParrot = new Parrot(19);
    System.out.println(myNewParrot.getName());

    Wolf babyWolf = new Wolf();
    System.out.println(babyWolf.getName());
  }
}
