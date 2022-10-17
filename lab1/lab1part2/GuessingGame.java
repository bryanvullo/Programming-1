public class GuessingGame {
  /**
   * this is a game in which the player needs to guess a random integer from 1-10.
   * this program will output if the guess is too low, high or if it's correct.
   */
  public static void main(String[] args) {
    System.out.println("Welcome to the Guessing Game!");
    Toolbox myToolbox = new Toolbox();

    int numberToGuess = myToolbox.getRandomInteger(10);
    int guessedNumber = myToolbox.readIntegerFromCmd();

    if (numberToGuess == guessedNumber) { //ask why not use this operator instead of using .equals
      System.out.println("right");
    } else {
      if (guessedNumber > numberToGuess) {
        System.out.println("too high");
      } else {
        System.out.println("too low");
      }
    }
  }
}