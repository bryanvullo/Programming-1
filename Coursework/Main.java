import java.util.List;
import music.MusicSheet;
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

    String[] score =
        {"57", "59", "62", "59", "66", "0", "66", "0", "64", "0", "57", "59", "62", "59", "64",
            "0", "64", "0", "62", "0", "0", "57", "59", "62", "59", "62", "0", "64", "61", "0",
            "57", "0", "57", "64", "0", "62", "0"};

    String[] hbdSong = {"C4", "C4", "D4", "none", "C4", "none", "F4", "none", "E4", "none", "none", "none",
        "C4", "C4", "D4", "none", "C4", "none", "G4", "none", "F4", "none", "none", "none",
        "C4", "C4", "C5", "none", "A4", "none", "F4", "none", "E4", "none", "D4", "none",
        "A#4", "A#4", "A4", "none", "F4", "none", "G4", "none", "F4", "none"};


    //using conductor
    Conductor myConductor = new Conductor("Carl", mySoundSystem);
    myConductor.registerMusician(myPianist);

    MusicSheet myComposition = new MusicSheet("RickRoll", "Moderato", 50);
    myComposition.addScore("Violin", List.of(hbdSong), true);
    myComposition.addScore("Cello", List.of(hbdSong), true);
    myComposition.addScore("Piano", List.of(hbdSong), true);
    myConductor.playComposition(myComposition);
  }
}
