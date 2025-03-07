package people.conductors;

import java.util.ArrayList;
import music.Composition;
import music.MusicScore;
import orchestra.Orchestra;
import people.Person;
import people.musicians.Musician;
import utils.SoundSystem;

public class Conductor extends Person {
  private SoundSystem soundSystem;
  private ArrayList<Musician> musicians = new ArrayList<Musician>();
  private Orchestra orchestra = new Orchestra(); //public because EcsBandAid uses this
  public Conductor(String name, SoundSystem soundSystem) {
    super(name);
    this.soundSystem = soundSystem;
  }

  public void registerMusician(Musician musician) {
    musicians.add(musician);
  }

  public boolean isRegistered(Musician musician) {
    return musicians.contains(musician);
  }

  public void deregisterMusician(Musician musician) {
    if (orchestra.isSeated(musician)) { //if musician is seated then
      orchestra.standUp(musician); //make them stand up before leaving the band
    }
    musicians.remove(musician);
  }

  public ArrayList<Musician> getMusicians() {
    return musicians;
  }

  public void playComposition(Composition composition) {
    //assign scores
    MusicScore[] scores = composition.getScores();
    for (MusicScore score : scores) { // loops through all the scores for each instrument
      for (Musician musician: musicians) { //loops through each musician assigned to the conductor
        if (score.getInstrumentID() == musician.getInstrumentID()) {
          //if already assigned a score
          if (musician.hasScore()){ //this ensures that scores of the same instrument
              //can be assigned to different musicians of that type
            //do nothing
          } else {
            //if the score is for the instrument that the musician in playing
            //and musician doesn't have a score
            musician.readScore(score.getNotes(), score.isSoft());
            int result = orchestra.sitDown(musician);
            switch (result) {
              case 0:
                System.out.println("Musician " + musician.getName() + " has sat down");
                break;
              case 1:
                System.err.println("There's no space for " + musician.getName());
                break;
              case 2:
                //musician is already sat down
                //not printing anything to remove terminal clutter as this is the most frequent case
                break;
              default:
                System.err.println("There's no space for " + musician.getName());
            } //prints out the result of the sitDown method
          }
        }
      }
    } //all the musicians are now assigned their scores
    // the composition is ready to be played
    for (int i = 0; i < composition.getLength(); i++) {
      try { //Thread.sleep needs to be wrapped in try/catch
        orchestra.playNextNote();
        Thread.sleep(composition.getNoteLength()); //how long the note is played for
      }
      catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    //composition has ended
    //each musician stands up and clears their score
    for (Musician musician : musicians) {
      musician.clearScore();
      if (orchestra.isSeated(musician)) {
        orchestra.standUp(musician);
      }
    }
    //composition has ended so stop the music
    soundSystem.init();
  }
}
