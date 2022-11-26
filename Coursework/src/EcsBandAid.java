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
    //plays 3 compositions a year
      //randomly choose 3 compositions
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
    for (Composition composition : compositionsToPlay) {
      System.out.println("Now playing " + composition.getName());
      bandConductor.playComposition(composition);
      System.out.println("");
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

  public static void main(String[] args) {
//    String musiciansFilename = args[0];
//    String compositionsFilename = args[1];
//    int years = Integer.parseInt(args[2]);

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
    for (int i = 0; i < years; i++) {
      myBand.performForAYear();
      System.out.println("The band has played for " + years + " years!");
    }
  }
}
