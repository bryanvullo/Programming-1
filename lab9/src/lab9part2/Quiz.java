package lab9part2;

import java.util.ArrayList;

public class Quiz {

  FlashCardReader myReader;
  ArrayList<FlashCard> myFlashCards = new ArrayList<FlashCard>();
  Toolbox myToolBox = new Toolbox();

  public Quiz(String fileName) {
    myReader = new FlashCardReader(fileName);
    myFlashCards = myReader.getFlashCards();
    play();
  }

  public void play() {
    for (FlashCard flashCard : myFlashCards) { //loops through the flash cards
      System.out.println(flashCard.getQuestion()); //prints question
      String answer = myToolBox.readStringFromCmd(); //takes answe
      if (answer.equals(flashCard.getAnswer())) { //compares answers
        System.out.println("right");
      } else {
        System.out.println("wrong");
        System.out.println("the correct answer is: " + flashCard.getAnswer());
      }
    }
  }

  public static void main(String[] args) {
    Quiz myQuiz = new Quiz("Questions.txt");
  }
}
