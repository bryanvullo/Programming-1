package lab9part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FlashCardReader {

  BufferedReader reader;
  ArrayList<FlashCard> flashCards = new ArrayList<FlashCard>();

  public FlashCardReader(String file) {
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      System.err.println(e + ": file not found");
    }
  }

  public String getLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      System.err.println(e + ": unable to read next line");
      return null;
    }
  }

  public boolean fileIsReady() {
    try {
      return reader.ready();
    } catch (Exception e) {
      System.err.println(e + ": file is not ready");
      return false;
    }
  }

  public ArrayList<FlashCard> getFlashCards() {
    boolean flag = true; //flag makes while loop run
    while (flag) {
      String line = getLine(); //gets next line of the text file
      if (line == null) { //if it's the end of the file then exit while loop
        flag = false;
      } else { //make a flash card using the line in the text file
        String[] questionAnswer = line.split(":");
        FlashCard myFlashCard = new FlashCard(questionAnswer[0], questionAnswer[1]);
        flashCards.add(myFlashCard); //add flash card to the array list
      }
    }
    return flashCards; //return the array list of flash cards
  }
}
