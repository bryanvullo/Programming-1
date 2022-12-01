import java.util.ArrayList;
import java.util.List;
import music.Composition;
import music.MusicSheet;
import orchestra.Orchestra;
import people.conductors.Conductor;
import people.musicians.Cellist;
import people.musicians.Musician;
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
        {"A3", "B3", "D4", "B3", "F#4", "none", "F#4", "none", "E4", "none"};

    String[] hbdSong =
        {"C4", "C4", "D4", "none", "C4", "none", "F4", "none", "E4", "none", "none", "none"};

    String[] LongHBDSong =
        {"C4", "C4", "D4", "none", "C4", "none", "F4", "none", "E4", "none", "none", "none",
            "C4", "C4", "D4", "none", "C4", "none", "G4", "none", "F4", "none", "none", "none",
            "C4", "C4", "C5", "none", "A4", "none", "F4", "none", "E4", "none", "D4", "none",
            "A#4", "A#4", "A4", "none", "F4", "none", "G4", "none", "F4", "none"};

    MusicSheet myRickRoll = new MusicSheet("RickRoll", "Moderato", 10);
    myRickRoll.addScore("Violin", List.of(score), true);
    myRickRoll.addScore("Cello", List.of(score), true);
    myRickRoll.addScore("Piano", List.of(score), true);

    MusicSheet myHBD = new MusicSheet("Happy Birthday", "Moderato", hbdSong.length);
    myHBD.addScore("Violin", List.of(hbdSong), true);
    myHBD.addScore("Cello", List.of(hbdSong), true);
    myHBD.addScore("Piano", List.of(hbdSong), true);

    ArrayList<Musician> myMusicians = new ArrayList<Musician>();
    myMusicians.add(myViolinist);
    myMusicians.add(myPianist);
    myMusicians.add(myCellist);

    ArrayList<Composition> myCompositions = new ArrayList<Composition>();
    myCompositions.add(myHBD);
    myCompositions.add(myRickRoll);

    EcsBandAid myBand = new EcsBandAid(mySoundSystem, myMusicians, myCompositions);
    for (int i = 0; i < 3; i++) {
      myBand.performForAYear();
    }
  }
}
