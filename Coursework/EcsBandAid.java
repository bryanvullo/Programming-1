import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import music.Composition;
import people.conductors.Conductor;
import people.musicians.Cellist;
import people.musicians.Musician;
import people.musicians.Pianist;
import people.musicians.Violinist;
import utils.SoundSystem;

public class EcsBandAid {
  private SoundSystem soundSystem;
  private ArrayList<Musician> musicians = new ArrayList<Musician>();
  private ArrayList<Composition> compositions = new ArrayList<Composition>();
  private Conductor bandConductor;

  public EcsBandAid(SoundSystem soundSystem, ArrayList<Musician> musicians,
      ArrayList<Composition> compositions) {
    this.soundSystem = soundSystem;
    this.musicians = musicians;
    this.compositions = compositions;
    bandConductor = new Conductor("Bryan", soundSystem);
  }

  public void performForAYear() {
    //TODO 1) randomly choose 3 compositions
    ArrayList<Composition> compositionsToPlay = new ArrayList<Composition>();
    for (int i = 0; i < 3; i++) {
      Collections.shuffle(compositions); //shuffles the compositions ArrayList
      Composition composition = compositions.get(0); //gets the first random element
      //as it gets shuffled every time the method is called, the first element should always be random
      compositionsToPlay.add(composition);
    }
    //print out the compositions to play
    System.out.println("The compositions to be played are: ");
    for (Composition composition : compositionsToPlay) {
      System.out.println(composition.getName());
    }
    //TODO 2) invite musicians
    //I will implement that 70% of each musician will accept the invite
    //ordered randomly, with first come first served basis
    Collections.shuffle(musicians); //shuffles the musicians ArrayList so it's random
    for (Musician musician : musicians) {
      if (bandConductor.isRegistered(musician)) {
        //don't invite them
      } else {
        System.out.println(musician.getName() + " has been invited");
        float random = (float) Math.random(); //random number between 0 and 1
        if (random <= 0.7) { //musician has accepted the invite (70% acceptance rate)
          bandConductor.registerMusician(musician);
          System.out.println(musician.getName() + " has accepted the invite");
        } else { //else skip to the next musician in the world
          System.out.println(musician.getName() + " has declined the invite");
        }
      }
    }
    System.out.println("");
    //TODO 3) perform the composition
    for (Composition composition : compositionsToPlay) {
      System.out.println("Now playing " + composition.getName());
      bandConductor.playComposition(composition);
      System.out.println("");
    }
    //TODO 4) each musician IN the band leaves with chance of 50%
    ArrayList<Musician> musiciansToRemove = new ArrayList<Musician>();
    for (Musician musician : bandConductor.getMusicians()) { //each musician which is registered
      float random = (float) Math.random(); //random number from 0 to 1
      if (random <= 0.5) { //50% chance they leave the band
        musiciansToRemove.add(musician);
        System.out.println(musician.getName() + " has left the band");
      } else {
        System.out.println(musician.getName() + " has remained in the band");
      }
    }
    //I  remove them outside the previous loop to not encounter ConcurrentModificationException
    for (Musician musician : musiciansToRemove) {
      bandConductor.deregisterMusician(musician); //remove from the band
    }
  }


  //static methods for run time execution
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

  public static void main(String[] args) {
    String musiciansFilename =  args[0];
    String compositionsFilename =  args[1];
    int years = Integer.parseInt(args[2]);

    SoundSystem mySoundSystem = null;
    Scanner musicianReader;
    Scanner compositionReader;
    Pattern name = Pattern.compile("Name", Pattern.CASE_INSENSITIVE);
    Pattern tempo = Pattern.compile("Tempo", Pattern.CASE_INSENSITIVE);
    Pattern length = Pattern.compile("Length", Pattern.CASE_INSENSITIVE);
    ArrayList<Musician> myMusicians = new ArrayList<Musician>();
    ArrayList<Composition> myCompositions = new ArrayList<Composition>();

    //init the sound system
    try {
      mySoundSystem = new SoundSystem();
    } catch (Exception e) {
      System.err.println(e);
      System.exit(0); //quits the program
    }

    //create the scanner objects
    musicianReader = createReader(musiciansFilename);
    compositionReader = createReader(compositionsFilename);

    //create the musicians collection
    while (musicianReader.hasNextLine()) {
      String line = musicianReader.nextLine();
      line.replace(")",""); //removes the trailing ) character at the end
      String[] words = line.split("\\(" ); //splits the line into the two musician and instrument
      Musician musician = null;
      switch (words[1]) {
        case "Piano":
          musician = new Pianist(words[0], mySoundSystem);
          break;
        case "Cello":
          musician = new Cellist(words[0], mySoundSystem);
          break;
        case "Violin":
          musician = new Violinist(words[0], mySoundSystem);
          break;
        default:
          System.err.println("we only accept piano, cello and violin musicians");
          System.exit(0); //quits the program
      }
      myMusicians.add(musician);
    }

    //create the compositions collection
    while (compositionReader.hasNextLine()) {
      String line = compositionReader.nextLine();
      if (name.matcher(line).find()) {
        //name
      } else if (tempo.matcher(line).find()) {
        //tempo
      } else if (length.matcher(line).find()) {
        //length
      } else {
        //score
      }
    }
  }
}
