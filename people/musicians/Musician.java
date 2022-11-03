package people.musicians;

interface Musician {

  public abstract void setSeat(int seat);

  public abstract void readScore(int[] notes, boolean soft);

  public abstract void playNextNote();
}
