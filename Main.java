import people.musicians.Cellist;
import people.musicians.Pianist;
import people.musicians.Violinist;
import utils.SoundSystem;

public class Main {
  static SoundSystem mySoundSystem;
  public static void main(String[] args) {
    try {
      mySoundSystem = new SoundSystem();
    }
    catch (Exception e) {
      System.out.println(e);
    }
    Violinist myViolinist = new Violinist("Violet", mySoundSystem,1);
    Pianist myPianist = new Pianist("Piero", mySoundSystem, 2);
    Cellist myCellist = new Cellist("Ciara", mySoundSystem, 3);

    int[] score = {67,65,67,65,67,65,67,65,67,0,0,67,65,67,65,67,67};
    myViolinist.setSeat(5);
    myViolinist.readScore(score, true);

    try {
      while (myViolinist.nextNote.hasNext()) {
        myViolinist.playNextNote();
        Thread.sleep(350);
      }
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}
