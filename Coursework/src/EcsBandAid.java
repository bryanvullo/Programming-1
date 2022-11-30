import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import music.Composition;
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

  public void setCompositionsToPlay(ArrayList<Composition> compositionsToPlay) {
    this.compositionsToPlay = compositionsToPlay;
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
        Json.saveTheYear(compositionsToPlay, currentCompositionIndex, this, currentYear, years);
        System.out.println("the current information has been saved");
        System.exit(0);
      } else {
        input = "P";
      }
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
    System.out.println("");

      //invite musicians
    //I will implement that 70% of each musician will accept the invite
    //ordered randomly, with first come first served basis
    Collections.shuffle(musicians); //shuffles the musicians ArrayList so it's random
    for (Musician musician : musicians) {
      if (bandConductor.isRegistered(musician)) {
        //don't invite them as they're already registered in the band
      } else {
        float random = (float) Math.random(); //random number between 0 and 1
        if (random <= 0.7) { //musician has accepted the invite (70% acceptance rate)
          bandConductor.registerMusician(musician);
          System.out.println(musician.getName() + " has been invited and accepted the invite");
        } else { //else skip to the next musician in the world
          System.out.println(musician.getName() + " has been invited and declined the invite");
        }
      }
    }
    System.out.println("");

      //perform the composition
    for (int i = 0; i < 3; i++) {
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

  public void resumeSave() {
    for (int i = 0; i < compositionsToPlay.size(); i++) {
      Composition composition = compositionsToPlay.get(i);
      System.out.println("Now playing " + composition.getName());
      bandConductor.playComposition(composition);
      System.out.println("");
      musicPlayer(i);
    }
    currentYear = currentYear + 1;
    System.out.println("The band has played for " + currentYear + " years!");
    System.out.println();
    for (; currentYear < years; currentYear++) {
      performForAYear();
      System.out.println("The band has played for " + currentYear + " years!");
    }
  }

  public void registerMusicians(ArrayList<Musician> musicians) {
    for (Musician musician : musicians) {
      bandConductor.registerMusician(musician);
    }
  }

  public static void main(String[] args) {
//    String musiciansFilename = args[0];
//    String compositionsFilename = args[1];
//    int years = Integer.parseInt(args[2]);

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
          myBand.resumeSave();
          flag = false;
        } else if (input.equals("N")) {
          //starts new simulation
          flag = false;
        }
      }
    }

    //testing/debugging purposes
    String musiciansFilename =  "musicians.txt";
    String compositionsFilename =  "compositions.txt";
    int years = 3;

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

    for (myBand.currentYear = 0;myBand.currentYear < myBand.years; myBand.currentYear++) {
      myBand.performForAYear();
      System.out.println("The band has played for " + myBand.currentYear + " years!");
    }
  }
}
