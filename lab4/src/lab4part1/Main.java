package lab4part1;

public class Main {
  public static void main(String[] args) {
    Toolbox myToolbox = new Toolbox();

    // outputs a multiplication table for a number the user inputs
    Integer value = myToolbox.readIntegerFromCmd();
    System.out.println("Multiplication Table for... ");
    for (int i=1; i<=20; i++) {
      System.out.println(value + " x " + i + " = " + (value*i));
    }

    // calculates how many consecutive intergers sum to over 500
    Integer total = 0;
    Integer number = 1;
    Integer numberOfIntegers = 0;
    while (total < 500) {
      total = total + number;
      number = ++number;
      numberOfIntegers = ++numberOfIntegers;
    }
    System.out.println("It takes " + numberOfIntegers + " integers from 0 to sum to over 500!");
  }
}
