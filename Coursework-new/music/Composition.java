package music;

import java.util.List;

public interface Composition {

  public abstract String getName();

  public abstract void addScore(String instrumentName, List<String> notes, boolean soft);

  public abstract MusicScore[] getScores();

  public abstract int getLength(); //how many notes to be played

  public abstract int getNoteLength(); //how long the note is in ms
}
