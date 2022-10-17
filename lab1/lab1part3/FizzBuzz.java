public class FizzBuzz { //create the FizzBuzz class
  public static void main(String[] args) { //declare the main method
    for (Integer i = 1; i < 61; i++) { //start a loop which counts from 1 to 60, incrementing by 1
      if (i % 3 != 0 && i % 5 != 0) { //if the number is not divisible by 3 or 5
        System.out.print(i); //print number
      }
      if (i % 3 == 0) { //if the number is divisible by 3
        System.out.print("Fizz"); //print Fizz
      }
      if (i % 5 == 0) { //if the number is divisible by 5
        System.out.print("Buzz"); //print Buzz
      }
      System.out.println(); //enter a new line in command line so each number or word sits on a new line
    }
  }
}