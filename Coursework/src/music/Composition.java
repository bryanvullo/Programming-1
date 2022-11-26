package music;

import java.util.List;

public interface Composition {

  String getName();

  void addScore(String instrumentName, List<String> notes, boolean soft);

  MusicScore[] getScores();

  int getLength(); //how many notes to be played

  int getNoteLength(); //how long the note is in ms
}
