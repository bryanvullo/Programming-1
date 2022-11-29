package people.musicians;

public interface Musician {

  void setSeat(int seat);

  void readScore(int[] notes, boolean soft);

  void playNextNote();

  int getInstrumentID();

  String getInstrument();

  String getName();

  void clearScore();

  boolean hasScore();
}
