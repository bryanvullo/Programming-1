import java.util.ArrayList;
import java.util.Collections;
import music.Composition;
import people.conductors.Conductor;
import people.musicians.Musician;
import utils.SoundSystem;

public class EcsBandAid {
  SoundSystem soundSystem;
  ArrayList<Musician> musicians = new ArrayList<Musician>();
  ArrayList<Composition> compositions = new ArrayList<Composition>();
  Conductor bandConductor;

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
    
    //TODO 3) perform the composition
    //TODO 4) each musician leaves with chance of 50%
  }
}
