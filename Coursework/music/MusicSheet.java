package music;

import java.util.ArrayList;
import java.util.List;

public class MusicSheet implements Composition {
  private String name;
  private String tempo;
  private int length;
  private ArrayList<MusicScore> scores = new ArrayList<MusicScore>();

  public MusicSheet(String name, String tempo, int length) {
    this.name = name;
    this.tempo = tempo;
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public void addScore(String instrumentName, List<String> notes, boolean soft) {
    int size = notes.size();
    int[] intNotes = new int[size];
    for (int i = 0; i < size; i++) {
      //this gets the string representation of the note at position i,
      //then convert it and then store it in the int array. (needed for score initialisation)
      intNotes[i] = convertNote(notes.get(i));
    }
    MusicScore musicScore = new MusicScore(instrumentName, intNotes, soft);
    scores.add(musicScore);
  }

  private int convertNote(String note) {
    switch (note) {
      case "C6":
        return 84;
      case "B5":
        return 83;
      case "A#5": case "Bb5":
        return 82;
      case "A5":
        return 81;
      case "G#5": case "Ab5":
        return 80;
      case "G5":
        return 79;
      case "F#5": case "Gb5":
        return 78;
      case "F5":
        return 77;
      case "E5":
        return 76;
      case "D#5": case "Eb5":
        return 75;
      case "D5":
        return 74;
      case "C#5": case "Db5":
        return 73;
      case "C5":
        return 72;
      case "B4":
        return 71;
      case "A#4": case "Bb4":
        return 70;
      case "A4":
        return 69;
      case "G#4": case "Ab4":
        return 68;
      case "G4":
        return 67;
      case "F#4": case "Gb4":
        return 66;
      case "F4":
        return 65;
      case "E4":
        return 64;
      case "D#4": case "Eb4":
        return 63;
      case "D4":
        return 62;
      case "C#4": case "Db4":
        return 61;
      case "C4":
        return 60;
      case "B3":
        return 59;
      case "A#3": case "Bb3":
        return 58;
      case "A3":
        return 57;
      case "G#3": case "Ab3":
        return 56;
      case "G3":
        return 55;
      case "F#3": case "Gb3":
        return 54;
      case "F3":
        return 53;
      case "E3":
        return 52;
      case "D#3": case "Eb3":
        return 51;
      case "D3":
        return 50;
      case "C#3": case "Db3":
        return 49;
      case "C3":
        return 48;
      default:
        return 0;
    }
  }

  public MusicScore[] getScores() {
    //this makes the ArrayList into an Array of type: MusicScore[], of size: scores.size()
    MusicScore[] scoresArray = scores.toArray(new MusicScore[scores.size()]);
    return scoresArray;
  }

  public int getLength() {
    return length;
  }

  private String getTempo() {
    return tempo;
  }

  public int getNoteLength() {
    switch (getTempo()) {
      case "Larghissimo":
        return 1500;
      case "Lento":
        return 1000;
      case "Andante":
        return 500;
      case "Moderato":
        return 300;
      case "Allegro":
        return 175;
      case "Presto":
        return 150;
      default:
        System.err.println("Tempo not specified: Defaulted to Allegro");
        return 175;
    }
  }
}
