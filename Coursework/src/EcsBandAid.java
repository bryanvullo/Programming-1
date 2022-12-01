import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import music.Composition;
import music.MusicScore;
import people.conductors.Conductor;
import people.musicians.Musician;
import utils.Helper;
import utils.SoundSystem;

public class EcsBandAid {

  private SoundSystem soundSystem;
  private ArrayList<Musician> musicians;
  private ArrayList<Composition> compositions;
  private ArrayList<Composition> compositionsToPlay;
  private Conductor bandConductor;
  private int years;
  private int currentYear;

  public EcsBandAid(SoundSystem soundSystem, ArrayList<Musician> musicians,
      ArrayList<Composition> compositions) {
    this.soundSystem = soundSystem;
    this.musicians = musicians;
    this.compositions = compositions;
    compositionsToPlay = new ArrayList<>();
    currentYear = 1;
    bandConductor = new Conductor("Bryan", this.soundSystem);
  }

  public ArrayList<Musician> getMusicians() {
    return musicians;
  }

  public ArrayList<Composition> getCompositions() {
    return compositions;
  }

  public Conductor getBandConductor() {
    return bandConductor;
  }

  public void setYears(int years) {
    this.years = years;
  }

  public void setCurrentYear(int currentYear) {
    this.currentYear = currentYear;
  }

  public int getYears() {
    return years;
  }

  public int getCurrentYear() {
    return currentYear;
  }

  public void setCompositionsToPlay(ArrayList<Composition> compositionsToPlay) {
    this.compositionsToPlay = compositionsToPlay;
  }

  public ArrayList<Composition> getCompositionsToPlay() {
    return compositionsToPlay;
  }

  public void musicPlayer(int currentCompositionIndex) {
    //music player options - pausing, resuming and saving
    Scanner inputReader = new Scanner(System.in); //allows me to get input from command
    System.out.print("enter 'P' to pause: ");
    String input = inputReader.nextLine();
    while (input.equals("P")) {
      //is paused
      System.out.print("enter 'R' to resume or 'S' to save: ");
      input = inputReader.nextLine();
      if (input.equals("R")) {
        //this breaks the loop and resumes
      } else if (input.equals("S")) {
        Json.saveTheYear(currentCompositionIndex, this);
        System.out.println("the current information has been saved");
        System.exit(0);
      } else {
        input = "P";
      }
    }
  }

  public void musiciansLeaveBand() {
    // each musician IN the band leaves with chance of 50%
    ArrayList<Musician> musiciansToRemove = new ArrayList<Musician>();
    for (Musician musician : bandConductor.getMusicians()) { //each musician which is registered
      float random = (float) Math.random(); //random number from 0 to 1
      if (random <= 0.5) { //50% chance they leave the band
        musiciansToRemove.add(musician);
        System.out.println(musician.getName() + " has left the band");
      } else {
        //the musician chose to remain in the band
      }
    }
    System.out.println("");
    //I  remove them outside the previous loop to not encounter ConcurrentModificationException
    for (Musician musician : musiciansToRemove) {
      bandConductor.deregisterMusician(musician); //remove from the band
    }
  }

  public void performForAYear() {
    //plays 3 compositions a year
    //randomly choose 3 compositions
    compositionsToPlay.clear();
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
    System.out.println();

    //finds out how many musicians of each type we need to perform the three compositions
    int violinistNeeded = 0;
    int pianistNeeded = 0;
    int cellistNeeded = 0;
    for (Composition composition : compositionsToPlay) {
      int violinistNeededForComposition = 0;
      int pianistNeededForComposition = 0;
      int cellistNeededForComposition = 0;
      //gets how many musicians of each type we need for this composition
      for (MusicScore score : composition.getScores()) {
        switch (score.getInstrumentName()) {
          case "Violin":
            violinistNeededForComposition += 1;
            break;
          case "Piano":
            pianistNeededForComposition += 1;
            break;
          case "Cello":
            cellistNeededForComposition += 1;
            break;
        }
      }
      //if more musicians are needed for this composition then the total counter is increased to
      // accommodate this composition
      if (violinistNeededForComposition > violinistNeeded) {
        violinistNeeded = violinistNeededForComposition;
      }
      if (pianistNeededForComposition > pianistNeeded) {
        pianistNeeded = pianistNeededForComposition;
      }
      if (cellistNeededForComposition > cellistNeeded) {
        cellistNeeded = cellistNeededForComposition;
      }
    }

    //invite musicians
    Collections.shuffle(musicians); //shuffles the musicians so it's random
    //invite the right amount of Violinist
    for (; violinistNeeded > 0; --violinistNeeded) {
      for (Musician musician : musicians) {
        if (!(bandConductor.isRegistered(musician)) && musician.getInstrument().equals("Violin")) {
          //invite them as they're a Violinist and aren't registered yet
          bandConductor.registerMusician(musician);
          System.out.println(musician.getName() + " has been invited");
          break;
        }
      }
    }
    //invite the right amount of Pianist
    for (; pianistNeeded > 0; --pianistNeeded) {
      for (Musician musician : musicians) {
        if (!(bandConductor.isRegistered(musician)) && musician.getInstrument().equals("Piano")) {
          //invite them as they're a Pianist and aren't registered yet
          bandConductor.registerMusician(musician);
          System.out.println(musician.getName() + " has been invited");
          break;
        }
      }
    }
    //invite the right amount of Cellist
    for (; cellistNeeded > 0; --cellistNeeded) {
      for (Musician musician : musicians) {
        if (!(bandConductor.isRegistered(musician)) && musician.getInstrument().equals("Cello")) {
          //invite them as they're a Cellist and aren't registered yet
          bandConductor.registerMusician(musician);
          System.out.println(musician.getName() + " has been invited");
          break;
        }
      }
    }
    System.out.println("");

    //perform the composition
    for (int i = 0; i < 3; i++) {
      Composition composition = compositionsToPlay.get(i);
      System.out.println("Now playing " + composition.getName());
      bandConductor.playComposition(composition);
      System.out.println();
      musicPlayer(i);
      try { // 2-second delay between compositions
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    //musicians leave at a 50% chance rate
    musiciansLeaveBand();
  }

  public void registerMusicians(ArrayList<Musician> musicians) {
    for (Musician musician : musicians) {
      bandConductor.registerMusician(musician);
    }
  }

  public void resumeSavedYear() {
    //print out the compositions to play
    System.out.println("The compositions to be played are: ");
    for (Composition composition : compositionsToPlay) {
      System.out.println(composition.getName());
    }
    System.out.println();
    //plays remaining songs of the year
    for (int i = 0; i < compositionsToPlay.size(); i++) {
      Composition composition = compositionsToPlay.get(i);
      System.out.println("Now playing " + composition.getName());
      bandConductor.playComposition(composition);
      System.out.println("");
      musicPlayer(i);
      try { // 2-second delay between compositions
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println("The band has played for " + currentYear + " years!");
    System.out.println();
    currentYear = currentYear + 1;

    //musicians leave at 50% chance rate
    musiciansLeaveBand();
  }

  public static EcsBandAid newSimulation(String musiciansFilename, String compositionsFilename,
      int years) {
    Scanner musicianReader;
    Scanner compositionReader;

    SoundSystem mySoundSystem;
    ArrayList<Musician> myMusicians;
    ArrayList<Composition> myCompositions;

    //create the sound system
    mySoundSystem = Helper.createSoundSystem();

    //create the scanner objects
    musicianReader = Helper.createReader(musiciansFilename);
    compositionReader = Helper.createReader(compositionsFilename);

    //create the musicians collection
    myMusicians = Helper.createMusicianArrayList(mySoundSystem, musicianReader);

    //create the compositions collection
    myCompositions = Helper.createCompositionArrayList(compositionReader);

    // so now we have all three objects we need to create the EcsBandAid Object
    // the SoundSystem, the musician collection, and the composition collection
    EcsBandAid myBand = new EcsBandAid(mySoundSystem, myMusicians, myCompositions);
    myBand.setYears(years);
    return myBand;
  }

  public void runSimulation() {
    for (; currentYear <= years; currentYear++) {
      performForAYear();
      System.out.println("The band has played for " + currentYear + " years!");
    }
  }

  public static void main(String[] args) {
//    String musiciansFilename = args[0];
//    String compositionsFilename = args[1];
//    int years = Integer.parseInt(args[2]);

    //testing/debugging purposes
    String musiciansFilename = "musicians.txt";
    String compositionsFilename = "compositions.txt";
    int years = 5;

    File saveFile = new File("savedSimulation.json");
    if (saveFile.exists()) {
      Scanner inputReader = new Scanner(System.in); //allows me to get input from command
      boolean flag = true;
      while (flag) {
        System.out.println("Would you like to resume the last saved simulation?");
        System.out.print("Enter 'Y' or 'N': ");
        String input = inputReader.nextLine();
        if (input.equals("Y")) {
          //resumes last save
          EcsBandAid myBand = Json.reloadTheYear();
          myBand.resumeSavedYear();
          myBand.runSimulation();
          flag = false;
        } else if (input.equals("N")) {
          //starts new simulation
          EcsBandAid myBand = newSimulation(musiciansFilename, compositionsFilename, years);
          myBand.runSimulation();
          flag = false;
        }
      }
    }
    System.exit(0);
  }
}
