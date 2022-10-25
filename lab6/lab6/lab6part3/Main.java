package lab6part3;

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
      myWolf.eat(myPlant);
      myRabbit.eat(myMeat);
    }
    catch (Exception e) {
      System.err.println(e);
    }

  }
}
