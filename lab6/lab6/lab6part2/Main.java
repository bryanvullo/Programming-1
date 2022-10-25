package lab6part2;

public class Main {
  public static void main(String[] args) {
    Parrot myParrot = new Parrot("Peter", 3);
    Wolf myWolf = new Wolf("Watson", 12);

    System.out.println(myParrot.getName() + ": " + myParrot.getAge());
    System.out.println(myWolf.getName() + ": " + myWolf.getAge());

    myParrot.makeNoise();
    myWolf.makeNoise();

    Plant myPlant = new Plant("Spinach");
    Meat myMeat = new Meat("Beef");

    System.out.println(myPlant.getName());
    System.out.println(myMeat.getName());
  }
}
