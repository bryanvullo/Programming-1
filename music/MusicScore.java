package music;

public class MusicScore {

  String instrumentName;
  private int[] notes;
  private boolean soft;
  private int instrumentID;

  public MusicScore(String instrumentName, int[] notes, boolean soft) {
    this.instrumentName = instrumentName;
    this.notes = notes;
    this.soft = soft;

    //assigns correct instrument ID depending on instrument name
    switch (this.instrumentName) {
      case "Cello":
        instrumentID = 43;
      case "Violin":
        instrumentID = 41;
      case "Piano":
        instrumentID = 1;
    }
  }

  public int getInstrumentID() {
    return instrumentID;
  }

  public int[] getNotes() {
    return notes;
  }

  public boolean isSoft() {
    return soft;
  }
}
