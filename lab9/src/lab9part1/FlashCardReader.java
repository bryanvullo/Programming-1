package lab9part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FlashCardReader {

  BufferedReader reader;

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
}
