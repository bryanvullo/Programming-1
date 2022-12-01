package music;

import java.util.ArrayList;
import java.util.List;

public class CompositionMock implements Composition {

  private String name;
  List<MusicScore> scores = new ArrayList<MusicScore>();
  int length;

  public CompositionMock(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void addScore(String instrumentName, List<String> notes, boolean soft) {
    length = notes.size();
    int[] intNotes = new int[length];
    for (int i = 0; i < length; i++) {
      intNotes[i] = Integer.parseInt(notes.get(i));
    }
    MusicScore score = new MusicScore(instrumentName, intNotes, soft);
    scores.add(score);
  }

  public MusicScore[] getScores() {
    MusicScore[] musicScores = new MusicScore[scores.size()];
    int i = 0;
    for (MusicScore score : scores) {
      musicScores[i] = score;
      i += 1;
    }
    return musicScores;
  }

  public int getLength() {
    return length;
  }

  public int getNoteLength() {
    return 175;
  }

  public String getTempo() {
    return getTempo();
  }
}
