package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import music.Composition;
import music.MusicSheet;
import people.musicians.Cellist;
import people.musicians.Musician;
import people.musicians.Pianist;
import people.musicians.Violinist;

public class Helper {

  public static Scanner createReader(String filename) {
    Scanner reader = null;
    try {
      File file = new File(filename);
      reader = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.err.println(e);
      System.exit(0); //quits the program
    }
    return reader;
  }

  public static SoundSystem createSoundSystem() {
    SoundSystem mySoundSystem = null;
    try {
      mySoundSystem = new SoundSystem();
    } catch (Exception e) {
      System.err.println(e);
      System.exit(0); //quits the program
    }
    return mySoundSystem;
  }

  public static ArrayList<Musician> createMusicianArrayList(SoundSystem mySoundSystem, Scanner musicianReader) {
    ArrayList<Musician> myMusicians = new ArrayList<Musician>();

    while (musicianReader.hasNextLine()) {
      String line = musicianReader.nextLine();
      line = line.replaceAll("\\)",""); //removes the trailing ) character at the end
      String[] words = line.split("\\(" ); //splits the line into the two musician and instrument
      Musician musician = null;
      if (words[1].equals("Piano")) {
        musician = new Pianist(words[0], mySoundSystem);
      } else if (words[1].equals("Cello")) {
        musician = new Cellist(words[0], mySoundSystem);
      } else if (words[1].equals("Violin")) {
        musician = new Violinist(words[0], mySoundSystem);
      } else {
        System.err.println("we only accept piano, cello and violin musicians, given " + words[1]);
        System.err.println("defaulted to piano"); //handled 'gracefully'
        musician = new Pianist(words[0], mySoundSystem);
      }
      myMusicians.add(musician);
    }
    return myMusicians;
  }

  public static ArrayList<Composition> createCompositionArrayList(Scanner compositionReader) {
    ArrayList<Composition> myCompositions = new ArrayList<Composition>();

    Pattern namePattern = Pattern.compile("Name", Pattern.CASE_INSENSITIVE);
    Pattern tempoPattern = Pattern.compile("Tempo", Pattern.CASE_INSENSITIVE);
    Pattern lengthPattern = Pattern.compile("Length", Pattern.CASE_INSENSITIVE);

    //initialising variables
    Composition currentComposition = null;
    String name = null;
    String tempo = null;
    int length;
    //create the compositions collection
    while (compositionReader.hasNextLine()) {
      String line = compositionReader.nextLine();
      if (namePattern.matcher(line).find()) {
        //name
        currentComposition = null; //resets the object
        //resets the currentComposition object as we encounter a new composition in the text file
        String[] words = line.split(": ");
        name = words[1];

      } else if (tempoPattern.matcher(line).find()) {
        //tempo
        line = line.replaceAll(" ", "");
        String[] words = line.split(":");
        tempo = words[1];

      } else if (lengthPattern.matcher(line).find()) {
        //length
        line = line.replaceAll(" ", "");
        String[] words = line.split(":");
        length = Integer.parseInt(words[1]);

        //end of new composition so create the new object
        currentComposition = new MusicSheet(name, tempo, length);
        myCompositions.add(currentComposition);

      } else {
        //score
        String instrumentName;
        boolean soft = true; //true is a placeholder for now
        String[] notes;

        line = line.replaceAll(" ", "");
        String[] words = line.split(",", 3);
        switch (words[0]) { //validates input of instrument
          case "Piano":
            break;
          case "Cello":
            break;
          case "Violin":
            break;
          default:
            System.err.println("we only accept piano, cello and violin musicians, given " + words[0]);
            System.err.println("defaulted to Piano");
            words[0] = "Piano"; //dealing with it "gracefully"
        }
        instrumentName = words[0];

        switch (words[1]) { //validates input of loudness
          case "soft":
            soft = true;
            break;
          case "loud":
            soft = false;
            break;
          default:
            System.err.println("we only accept soft or loud loudness, given " + words[1]);
            System.err.println("defaulted to soft");
            soft = true; //dealing with it "gracefully"
        }

        String notesString = words[2]; //the list of notes
        notesString = notesString.replaceAll("\\{", ""); //remove {
        notesString = notesString.replaceAll("}", ""); //remove }
        notesString = notesString.replaceAll(" ", ""); // removes whitespace
        notes = notesString.split(","); //each individual note in an array

        currentComposition.addScore(instrumentName, Arrays.asList(notes), soft);
      }
    }
    return myCompositions;
  }
}
