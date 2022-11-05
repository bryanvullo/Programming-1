package lab9part3;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Quiz {
  FlashCardReader myReader;
  ArrayList<FlashCard> myFlashCards = new ArrayList<FlashCard>();
  Toolbox myToolBox = new Toolbox();

  ArrayList<String> results = new ArrayList<String>();

  public Quiz(String fileName) {
    myReader = new FlashCardReader(fileName);
    myFlashCards = myReader.getFlashCards();
    play();
  }

  public void play() {
    System.out.println("Would you like to save your results? [Y/N]"); //asks user if they would like to save their result
    String saveResults = myToolBox.readStringFromCmd();
    while (!(saveResults.equals("Y") || saveResults.equals("N"))) { //validates answer
      System.out.println("Would you like to save your results? [Y/N]");
      saveResults = myToolBox.readStringFromCmd();
    }
    int correctCounter = 0; //initialises counters to save results
    int questionCounter = 0;
    for (FlashCard flashCard : myFlashCards) { //loops through the flash cards
      System.out.println(flashCard.getQuestion()); //prints question
      String answer = myToolBox.readStringFromCmd(); //takes answe
      if (answer.equals(flashCard.getAnswer())) { //compares answers
        System.out.println("right");
        results.add(flashCard.getQuestion() + "," + answer + ",right"); //adds the result to the results array
        correctCounter += 1; //increments counter for correct questions
      }
      else {
        System.out.println("wrong");
        System.out.println("the correct answer is: " + flashCard.getAnswer());
        results.add(flashCard.getQuestion() + "," + answer + ",wrong"); //adds the result to the results array
      }
      questionCounter += 1; //increments counter for total questions
    }
    if (saveResults.equals("Y")) { //if user chose to save their results, then call save method
      float percentage = (float) correctCounter / questionCounter * 100; //calculates percentage of correct questions
      results.add(correctCounter + "," + questionCounter + "," + percentage); //adds the final score to the array
      save();
    }
  }

  public void save() { //writes all the results in the results array into the save.txt file
    PrintStream printStream;
    try {
      printStream = new PrintStream("save.txt");
      for (String result : results) {
        printStream.println(result);
      }
    }
    catch (FileNotFoundException e) {
      System.err.println(e + ": file not found");
    }
  }

  public static void main(String[] args) {
    Quiz myQuiz = new Quiz("Questions.txt");
  }
}
