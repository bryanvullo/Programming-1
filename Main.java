import java.util.List;
import music.CompositionMock;
import orchestra.Orchestra;
import people.conductors.Conductor;
import people.musicians.Cellist;
import people.musicians.Pianist;
import people.musicians.Violinist;
import utils.SoundSystem;

public class Main {

  static SoundSystem mySoundSystem;

  public static void main(String[] args) {
    try {
      mySoundSystem = new SoundSystem();
    } catch (Exception e) {
      System.out.println(e);
    }
    Violinist myViolinist = new Violinist("Violet", mySoundSystem, 1);
    Pianist myPianist = new Pianist("Piero", mySoundSystem, 2);
    Cellist myCellist = new Cellist("Ciara", mySoundSystem, 3);

    Orchestra myOrchestra = new Orchestra();

    //testing methods
    System.out.println(myOrchestra.isSeated(myCellist));
    myOrchestra.sitDown(myCellist);
    System.out.println(myOrchestra.isSeated(myCellist));
    myOrchestra.standUp(myCellist);
    System.out.println(myOrchestra.isSeated(myCellist));
    System.out.println(myCellist.getSeat()); //this should be null, it is not

    String[] score =
        {"57", "59", "62", "59", "66", "0", "66", "0", "64", "0", "57", "59", "62", "59", "64",
            "0", "64", "0", "62", "0", "0", "57", "59", "62", "59", "62", "0", "64", "61", "0",
            "57", "0", "57", "64", "0", "62"};

    List<String> scoreList = List.of(score);

    //using conductor
    Conductor myConductor = new Conductor("Carl", mySoundSystem);
    myConductor.registerMusician(myCellist);
    myConductor.registerMusician(myViolinist);
    myConductor.registerMusician(myPianist);

    CompositionMock myComposition = new CompositionMock("RickRoll");
    myComposition.addScore("Violin", scoreList, true);
    myComposition.addScore("Cello", scoreList, true);
    myComposition.addScore("Piano", scoreList, true);
    myConductor.playComposition(myComposition);
  }
}
